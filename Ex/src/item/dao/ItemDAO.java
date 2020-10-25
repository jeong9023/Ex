package item.dao;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import item.vo.ItemBean;
import member.vo.MemberBean;
import order.vo.orderBean;

public class ItemDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static ItemDAO instance;

	private ItemDAO() {
	}

	public static ItemDAO getInstance() {
		if (instance == null) {
			instance = new ItemDAO();
		}

		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 상품 등록
	public int insertArticle(ItemBean itemBean) {
		int insertCount = 1;

		try {
			sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0); // it_no
			pstmt.setString(2, itemBean.getName());
			pstmt.setString(3, itemBean.getMemberID());
			pstmt.setTimestamp(4, itemBean.getWriteTime());
			pstmt.setString(5, itemBean.getEndTime().substring(0, 16));
			pstmt.setInt(6, itemBean.getStartPrice());
			pstmt.setInt(7, itemBean.getMaxPrice());
			pstmt.setInt(8, itemBean.getNowPrice());
			pstmt.setInt(9, itemBean.getSuccessfulPrice());
			pstmt.setString(10, itemBean.getContent());
			pstmt.setInt(11, itemBean.getDeliveryPrice());
			pstmt.setString(12, itemBean.getCategory());
			pstmt.setString(13, itemBean.getThumbnail());
			pstmt.setInt(14, itemBean.getIsTicket());
			pstmt.setInt(15, 0); // it_is_delete

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	
	// 마켓 프로필 등록 했는 지 확인
	public int isMarketOpened(String memberID) {
		int isOpened = 0;

		try {
			sql = "SELECT tn_flag FROM thumbnail WHERE mb_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				isOpened = rs.getInt("tn_flag");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return isOpened;
	}

	// 글쓴이인지 판별
	public boolean isWriter(int no, String memberID) {
		boolean isWriter = false;

		try {
			sql = "SELECT mb_id FROM item WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString("mb_id").equals(memberID)) {
					isWriter = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return isWriter;
	}

	// 상품 정보 가져오기
	public ItemBean selectArticle(int no) {
		ItemBean article = null;

		try {
			sql = "SELECT * FROM item WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new ItemBean();
				article.setNo(rs.getInt("it_no"));
				article.setName(rs.getString("it_name"));
				article.setMemberID(rs.getString("mb_id"));
				article.setWriteTime(rs.getTimestamp("it_write_datetime"));
				article.setEndTime(rs.getString("it_end_datetime").substring(0, 16));
				article.setStartPrice(rs.getInt("it_start_price"));
				article.setMaxPrice(rs.getInt("it_max_price"));
				article.setNowPrice(rs.getInt("it_now_price"));
				article.setSuccessfulPrice(rs.getInt("it_successful_price"));
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setContent(rs.getString("it_content"));
				article.setCategory(rs.getString("it_category"));
				article.setThumbnail(rs.getString("it_thumbnail"));
				article.setIsDelete(rs.getInt("it_is_ticket"));
				article.setIsDelete(rs.getInt("it_is_delete"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}
	
	public int selectOrderArticle(int no) {
		int orderarticle = 0;

		try {
			sql = "SELECT count(*) FROM trade WHERE it_no = ? and (tr_flag = 1 or tr_flag =2)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				orderarticle=rs.getInt("count(*)");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return orderarticle;
	}
	
	
	// 상품 수정
	public int updateArticle(ItemBean itemBean) {
		int isSuccess = 0;

		try {
			sql = "UPDATE item "
				+ "SET it_name = ?, it_end_datetime = ?, it_start_price = ?, it_max_price = ?, it_content = ?, it_delivery_price = ?, it_category = ? "
				+ "WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemBean.getName());
			pstmt.setString(2, itemBean.getEndTime());
			pstmt.setInt(3, itemBean.getStartPrice());
			pstmt.setInt(4, itemBean.getMaxPrice());
			pstmt.setString(5, itemBean.getContent());
			pstmt.setInt(6, itemBean.getDeliveryPrice());
			pstmt.setString(7, itemBean.getCategory());
			pstmt.setInt(8, itemBean.getNo());
			isSuccess = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return isSuccess;
	}
	
	// 낙찰 또는 유찰 상품인지
	public int isPossible(int no) {
		int isPossible = 0;

		try {
			sql = "SELECT tr_flag FROM trade WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getInt("tr_flag") == 2) { // 낙찰
					isPossible = 2;
				} else if(rs.getInt("tr_flag") == 3) { // 유찰
					isPossible = 3;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return isPossible;
	}
	
	// 땅땅티켓 갯수 가져오기
	public MemberBean getTicket(String memberID) {
		MemberBean article = null;
		
		try {
			sql = "SELECT mb_ticket FROM member WHERE mb_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				article = new MemberBean();
				article.setTicket(rs.getInt("mb_ticket"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}
	
	// 땅땅티켓 1개 사용하기
	public int updateTicket(String memberID) {
		int updateCount = 0;
		
		try {
			sql = "UPDATE member SET mb_ticket = mb_ticket - ? WHERE mb_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1); // 1개
			pstmt.setString(2, memberID);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}
	
	// 활동제한 걸려있는지 확인
	public boolean isBan(String memberID) {
		boolean isBan = false;
		
		try {
			sql = "SELECT mb_ban_datetime FROM member WHERE mb_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				// 활동제한 걸려있다면
				if(rs.getTimestamp("mb_ban_datetime") != null) {
					isBan = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return isBan;
	}

	// 라이브 리스트 상품 전체 갯수
	public int selectListCount() {
		int listCount = 0;

		try {
			sql = "SELECT COUNT(it_no) FROM item";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	// 라이브 리스트 출력
	public ArrayList<ItemBean> selectArticleList(int page, int limit) {
		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		ArrayList<ItemBean> articleList = new ArrayList<ItemBean>();

		try {
			// 닉네임을 가져오기 위해서 LEFT JOIN
			sql = "SELECT a.it_now_price,a.it_no, a.it_name, a.mb_id, a.it_content, a.it_thumbnail, a.it_write_datetime, a.it_end_datetime, a.it_max_price, a.it_start_price, a.it_delivery_price, a.it_category, a.it_is_ticket, a.it_is_delete, b.mb_nick "
				+ "FROM item a LEFT JOIN member b "
				+ "ON a.mb_id = b.mb_id "
				+ "ORDER BY a.it_is_ticket=1 DESC, a.it_no DESC LIMIT ?, ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean article = new ItemBean();
				article.setNo(rs.getInt("it_no"));
				article.setName(rs.getString("it_name"));
				article.setMemberID(rs.getString("mb_id"));
				article.setContent(rs.getString("it_content"));
				article.setThumbnail(rs.getString("it_thumbnail"));
				article.setWriteTime(rs.getTimestamp("it_write_datetime"));
				article.setEndTime(rs.getString("it_end_datetime").substring(0, 16));
				article.setMaxPrice(rs.getInt("it_max_price"));
				article.setStartPrice(rs.getInt("it_start_price"));
				article.setNowPrice(rs.getInt("it_now_price"));
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setCategory(rs.getString("it_category"));
				article.setIsTicket(rs.getInt("it_is_ticket"));
				article.setIsDelete(rs.getInt("it_is_delete"));
				article.setMemberNick(rs.getString("mb_nick"));

				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}

	public int addPrice(int it_no, int price) {
		
		
		int addPrice = 0;
		try {
			sql = "UPDATE item SET it_now_price = it_now_price+? WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setInt(2, it_no);
			addPrice = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return addPrice;
	}

	public void addUser(int it_no, String customerID) {
		try {
			sql = "INSERT INTO trade(tr_no,mb_id,tr_flag,tr_datetime,it_no) VALUES(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, customerID);
			pstmt.setInt(3, 1); // 입찰 플래그
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, it_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	}
	
	public void successful(int it_no, String memberID, String customerID, String endTime) {

		try {
			sql = "select mb_id from trade where it_no = ? and tr_flag = ? "
					+ "order by tr_datetime desc limit 1";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, it_no);
			pstmt.setInt(2, 1);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql = "select mb_id from trade where it_no = ? and tr_flag = ? "
						+ "order by tr_datetime desc limit 1";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, it_no);
				pstmt.setInt(2, 2);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					return;
					
				}else if(!rs.next()) {
				
				sql="update trade set tr_flag = 2 "
						+ "where tr_no IN (select tr_no from(select tr_no from trade where it_no = ? and tr_flag = ? "
						+ "order by tr_datetime desc limit 1)as temp) ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, it_no);
				pstmt.setInt(2, 1);
				pstmt.executeUpdate();
				
				}
			}else if(!rs.next()){
				sql = "select mb_id from trade where it_no = ? and tr_flag = ? "
						+ "order by tr_datetime desc limit 1";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, it_no);
				pstmt.setInt(2, 3);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					return;
					
				}else if(!rs.next()){
				
				sql="INSERT INTO trade VALUES(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setString(2, memberID);
				pstmt.setInt(3, 3);
				pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				pstmt.setInt(5, it_no);
				pstmt.executeUpdate();
				}

			
		}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
	}
	

	public void maxPrice(int it_no, int maxPrice, String customerID, int nowPrice, int price) {
		try {
			if(maxPrice<=nowPrice || maxPrice<=(nowPrice+price)) {
			sql = "INSERT INTO trade(tr_no,mb_id,tr_flag,tr_datetime,it_no) VALUES(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, customerID);
			pstmt.setInt(3, 2); // 입찰 플래그
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, it_no);
			pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	}
	
	
	// 라이브 리스트 상품 전체 갯수
		public int selectListCount(String minprice, String maxprice) {
			int listCount = 0;

			try {
				sql = "SELECT COUNT(it_no) FROM item WHERE it_now_price BETWEEN ? and ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, minprice);
				pstmt.setString(2, maxprice);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			return listCount;
		}

	public ArrayList<ItemBean> selectArticleList(int page, int limit, String minprice, String maxprice) {
		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		ArrayList<ItemBean> articleList = new ArrayList<ItemBean>();

		try {
			// 닉네임을 가져오기 위해서 LEFT JOIN
			sql = "SELECT a.it_now_price,a.it_no, a.it_name, a.mb_id, a.it_content, a.it_thumbnail, a.it_write_datetime, a.it_end_datetime, a.it_max_price, a.it_start_price, a.it_delivery_price, a.it_category, a.it_is_ticket, a.it_is_delete, b.mb_nick "
				+ "FROM item a LEFT JOIN member b "
				+ "ON a.mb_id = b.mb_id "
				+ "WHERE a.it_now_price BETWEEN ? and ?"
				+ "ORDER BY a.it_is_ticket=1 , a.it_no DESC LIMIT ?, ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, minprice);
			pstmt.setString(2, maxprice);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean article = new ItemBean();
				article.setNo(rs.getInt("it_no"));
				article.setName(rs.getString("it_name"));
				article.setMemberID(rs.getString("mb_id"));
				article.setContent(rs.getString("it_content"));
				article.setThumbnail(rs.getString("it_thumbnail"));
				article.setWriteTime(rs.getTimestamp("it_write_datetime"));
				article.setEndTime(rs.getString("it_end_datetime").substring(0, 16));
				article.setMaxPrice(rs.getInt("it_max_price"));
				article.setStartPrice(rs.getInt("it_start_price"));
				article.setNowPrice(rs.getInt("it_now_price"));
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setCategory(rs.getString("it_category"));
				article.setIsTicket(rs.getInt("it_is_ticket"));
				article.setIsDelete(rs.getInt("it_is_delete"));
				article.setMemberNick(rs.getString("mb_nick"));

				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	public ArrayList<ItemBean> selectArticleList(int page, int limit, String dress, String Antique,
			String Luxury,String entertainments, String Electronics,String product, 
			String books,String sports, String beauty, String freedelivery) {
		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		ArrayList<ItemBean> articleList = new ArrayList<ItemBean>();

		try {
			// 닉네임을 가져오기 위해서 LEFT JOIN
			sql = "SELECT a.it_now_price,a.it_no, a.it_name, a.mb_id, a.it_content, a.it_thumbnail, a.it_write_datetime, a.it_end_datetime, a.it_max_price, a.it_start_price, a.it_delivery_price, a.it_category, a.it_is_ticket, a.it_is_delete, b.mb_nick "
				+ "FROM item a LEFT JOIN member b "
				+ "ON a.mb_id = b.mb_id "
				+ "WHERE a.it_category like ? OR a.it_category like ? OR a.it_category like ? "
				+ "OR a.it_category like ? OR a.it_category like ? OR a.it_category like ? "
				+ "OR a.it_category like ? OR a.it_category like ? OR a.it_category like ?"
				+ "OR a.it_delivery_price like ?"
				+ "ORDER BY a.it_is_ticket=1 DESC, a.it_no DESC LIMIT ?, ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+dress+"%");
			pstmt.setString(2, "%"+Antique+"%");
			pstmt.setString(3, "%"+Luxury+"%");
			pstmt.setString(4, "%"+entertainments+"%");
			pstmt.setString(5, "%"+Electronics+"%");
			pstmt.setString(6, "%"+product+"%");
			pstmt.setString(7, "%"+books+"%");
			pstmt.setString(8, "%"+sports+"%");
			pstmt.setString(9, "%"+beauty+"%");
			pstmt.setString(10, freedelivery+"%");
			pstmt.setInt(11, startRow);
			pstmt.setInt(12, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean article = new ItemBean();
				article.setNo(rs.getInt("it_no"));
				article.setName(rs.getString("it_name"));
				article.setMemberID(rs.getString("mb_id"));
				article.setContent(rs.getString("it_content"));
				article.setThumbnail(rs.getString("it_thumbnail"));
				article.setWriteTime(rs.getTimestamp("it_write_datetime"));
				article.setEndTime(rs.getString("it_end_datetime").substring(0, 16));
				article.setMaxPrice(rs.getInt("it_max_price"));
				article.setStartPrice(rs.getInt("it_start_price"));
				article.setNowPrice(rs.getInt("it_now_price"));
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setCategory(rs.getString("it_category"));
				article.setIsTicket(rs.getInt("it_is_ticket"));
				article.setIsDelete(rs.getInt("it_is_delete"));
				article.setMemberNick(rs.getString("mb_nick"));

				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	
	public ArrayList<ItemBean> myItemArticleList(int page, int limit, String memberID) {
		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		ArrayList<ItemBean> articleList = new ArrayList<ItemBean>();

		try {
			// 닉네임을 가져오기 위해서 LEFT JOIN
			sql = "SELECT a.it_now_price,a.it_no, a.it_name, a.mb_id, a.it_content, a.it_thumbnail, a.it_write_datetime, a.it_end_datetime, a.it_max_price, a.it_start_price, a.it_delivery_price, a.it_category, a.it_is_ticket, a.it_is_delete, b.mb_nick "
				+ "FROM item a LEFT JOIN member b "
				+ "ON a.mb_id = b.mb_id "
				+ "WHERE a.mb_id like ?"
				+ "ORDER BY a.it_is_ticket=1 DESC, a.it_no DESC LIMIT ?, ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean article = new ItemBean();
				article.setNo(rs.getInt("it_no"));
				article.setName(rs.getString("it_name"));
				article.setMemberID(rs.getString("mb_id"));
				article.setContent(rs.getString("it_content"));
				article.setThumbnail(rs.getString("it_thumbnail"));
				article.setWriteTime(rs.getTimestamp("it_write_datetime"));
				article.setEndTime(rs.getString("it_end_datetime").substring(0, 16));
				article.setMaxPrice(rs.getInt("it_max_price"));
				article.setStartPrice(rs.getInt("it_start_price"));
				article.setNowPrice(rs.getInt("it_now_price"));
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setCategory(rs.getString("it_category"));
				article.setIsTicket(rs.getInt("it_is_ticket"));
				article.setIsDelete(rs.getInt("it_is_delete"));
				article.setMemberNick(rs.getString("mb_nick"));

				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}


}
