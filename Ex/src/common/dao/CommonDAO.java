package common.dao;

import static common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import common.vo.RandomCategoryBean;
import common.vo.ShopRankBean;
import item.vo.ItemBean;

public class CommonDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static CommonDAO instance;

	private CommonDAO() {
	}

	public static CommonDAO getInstance() {
		if (instance == null) {
			instance = new CommonDAO();
		}

		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 랜덤 카테고리 (최대 6개)
	public ArrayList<RandomCategoryBean> getRandomCategory() {
		ArrayList<RandomCategoryBean> articleList = null;

		try {
			articleList = new ArrayList<RandomCategoryBean>();

			// 낙찰 또는 유찰된 상품이 있다면 t.tr_no의 값은 1 이상, 아니라면 NULL
			sql = "SELECT i.it_no, i.it_category, i.it_thumbnail, i.it_name, t.tr_no "
				+ "FROM item i "
				+ "LEFT JOIN trade t ON i.it_no = t.it_no AND (t.tr_flag = 2 OR t.tr_flag = 3) "
				+ "ORDER BY RAND() LIMIT 6";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				// 낙찰 또는 유찰된 상품이라면 되돌리기
				if(rs.getInt("tr_no") > 0) continue;
				
				RandomCategoryBean list = new RandomCategoryBean();
				
				list.setItemNumber(rs.getInt("it_no"));
				list.setItemCategory(rs.getString("it_category"));
				list.setItemThumbnail(rs.getString("it_thumbnail"));
				list.setItemName(rs.getString("it_name"));
				
				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return articleList;
	}
	

	//상점랭킹
public ArrayList<ShopRankBean> getShopRank() {
		ArrayList<ShopRankBean> shopRank = new ArrayList<ShopRankBean>();
		
		try {
			String sql="SELECT tr.tr_flag, mb.mb_nick,it.mb_id, count(*) AS count"
					+ " FROM trade AS tr"
					+ " JOIN item AS it"
					+ " ON it.it_no = tr.it_no"
					+ " JOIN member AS mb"
					+ " ON it.mb_id = mb.mb_id"
					+ " WHERE tr.tr_flag =1"
					+ " GROUP BY mb_id"
					+ " ORDER BY count desc limit 5";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ShopRankBean shopRankList = new ShopRankBean();
				shopRankList.setMb_nick(rs.getString("mb_nick"));
				
				shopRank.add(shopRankList);
			}
		}catch (Exception e) {
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return shopRank;
	}

	/*
	 * 그래프
	 * 1. 키워드
	 * 2. 이용자수(가입자수)
	 * 3. 낙찰 가격대
	 */
	public Map<String, Map<String, Integer>> getChartdata(){
		Map<String, Map<String, Integer>> chartData = new HashMap<String, Map<String,Integer>>();
		
			//1. 키워드 파이 차트
			Map<String, Integer> keyword = new HashMap<String, Integer>();
		
		try {
			String sql = "SELECT * FROM ddang.keyword ORDER BY kw_search_count DESC LIMIT 0,7";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				keyword.put(rs.getString("kw_keyword"), rs.getInt("kw_search_count"));
			}
			
			if(keyword!=null) {
				chartData.put("keyword", keyword);
			}
			
			//2. 가입자 수 (컬럼차트)
			Map<String, Integer> numOfuser = new LinkedHashMap<String, Integer>();
			
			sql = "SELECT DATE_format(`mb_reg_datetime`,'%c월%d일') AS `date`, COUNT(*) AS `cnt` FROM ddang.member GROUP BY `date` ORDER BY `date` ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				numOfuser.put(rs.getString("date"), rs.getInt("cnt"));
			}
			
			if(numOfuser!=null) {
				chartData.put("numOfuser", numOfuser);
			}
			
			//낙찰가 분포
			Map<String, Integer> priceData = new LinkedHashMap<String, Integer>();
			
			for(int i=0;i<5;i++) {
				sql = "SELECT COUNT(it_successful_price) AS 'price_cnt' FROM ddang.item WHERE it_successful_price > ? AND it_successful_price <= ?";
				DecimalFormat formatter = new DecimalFormat("#,###");
				pstmt = con.prepareStatement(sql);
				
				int startPrice = (int)Math.pow(10, i+1);
				int endPrice = (int)Math.pow(10, i+2);
				String priceLabel = formatter.format(endPrice)+"원";
				
				pstmt.setInt(1, startPrice);
				pstmt.setInt(2, endPrice);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					priceData.put(priceLabel, rs.getInt("price_cnt"));
				}
			}
			
			if(priceData!=null) {
				chartData.put("priceData", priceData);
			}
			
		}catch (Exception e) {
			System.out.println("commonDAO - getChardata() : "+e.getMessage());
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return chartData;
	}
	
	// 광고상품
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
				+ "WHERE a.it_is_ticket='1'"
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

}
