package talk.dao;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import talk.vo.TalkBean;

public class TalkDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static TalkDAO instance;

	private TalkDAO() {
	}

	public static TalkDAO getInstance() {
		if (instance == null) {
			instance = new TalkDAO();
		}

		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 상품 번호로 출품자 회원 아이디 가져오기
	public String getMemberID(int itemNum) {
		String memberID = null;

		try {
			sql = "SELECT mb_id FROM item WHERE it_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				memberID = rs.getString("mb_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return memberID;
	}
	
	// 메시지 전송
	public int messageSend(TalkBean talkBean) {
		int sendCount = 1;

		try {
			sql = "INSERT INTO talk VALUES (?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, talkBean.getNo());
			pstmt.setInt(2, talkBean.getItemNumber());
			pstmt.setString(3, talkBean.getRecvMemberID());
			pstmt.setString(4, talkBean.getSendMemberID());
			pstmt.setTimestamp(5, talkBean.getSendDatetime());
			pstmt.setString(6, talkBean.getContent());

			sendCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return sendCount;
	}
	
	// 받은 톡방 리스트 불러오기
	public ArrayList<TalkBean> getRecvRoom(String memberID) {
		ArrayList<TalkBean> articleList = null;

		try {
			articleList = new ArrayList<TalkBean>();
			
			/*
			 * JOIN 1. 컬럼 it_no와 tk_send_mb_id를 기준으로 그룹을 묶어 마지막 톡(최근순 MAX tk_no)을 가져옴
			 * JOIN 2. 테이블 member에서 컬럼 mb_nick 가져옴
			 * JOIN 3. 테이블 thumbnail에서 컬럼 tn_source_name 가져옴 (회원가입 시 마켓 프로필 모두 기본값으로 저장하므로 등록 안했으면 기본값의 이미지로 저장됨)
			 * 
			 * 전체 가져온 데이터를 컬럼 tk_send_datetime을 기준으로 내림차순 정렬
			 */
			sql = "SELECT a.*, b.*, c.mb_nick, d.tn_source_name " 
				+ "FROM talk a " 
				+ "JOIN (SELECT it_no, tk_send_mb_id, MAX(tk_no) latest FROM talk GROUP BY it_no, tk_send_mb_id) b "
				+ "JOIN member c ON a.tk_send_mb_id = c.mb_id "
				+ "JOIN thumbnail d ON a.tk_send_mb_id = d.mb_id " 
				+ "WHERE a.tk_recv_mb_id = ? AND b.latest = a.tk_no AND a.tk_recv_mb_id <> a.tk_send_mb_id "
				+ "ORDER BY a.tk_send_datetime DESC;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SimpleDateFormat date = new SimpleDateFormat("YY-MM-dd");
				TalkBean list = new TalkBean();
					
				list.setItemNumber(rs.getInt("it_no"));
				list.setRecvMemberID(rs.getString("tk_recv_mb_id"));
				list.setSendMemberID(rs.getString("tk_send_mb_id"));
				list.setSendMemberNick(rs.getString("mb_nick"));
				list.setSendDatetime(rs.getTimestamp("tk_send_datetime"));
				list.setSendDatetimeToString(date.format(list.getSendDatetime()));
				list.setProfileImgName(rs.getString("tn_source_name"));

				// 문자열의 길이가 25자가 넘어갈 경우 최대 25자로 제한
				if(rs.getString("tk_content").length() > 25) { 
					list.setContent(rs.getString("tk_content").substring(0, 25) + "…");
				} else {
					list.setContent(rs.getString("tk_content"));
				}
				
				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	// 보낸 톡방 리스트 불러오기
	public ArrayList<TalkBean> getSendRoom(String memberID) {
		ArrayList<TalkBean> articleList = null;

		try {
			articleList = new ArrayList<TalkBean>();
			
			/*
			 * JOIN 1. 컬럼 it_no와 tk_send_mb_id를 기준으로 그룹을 묶어 마지막 톡(최근순 MAX tk_no)을 가져옴
			 * JOIN 2. 테이블 member에서 컬럼 mb_nick 가져옴
			 * JOIN 3. 테이블 thumbnail에서 컬럼 tn_source_name 가져옴 (회원가입 시 마켓 프로필 모두 기본값으로 저장하므로 등록 안했으면 기본값의 이미지로 저장됨)
			 * 
			 * 전체 가져온 데이터를 컬럼 tk_send_datetime을 기준으로 내림차순 정렬
			 */
			sql = "SELECT a.*, b.*, c.mb_nick, d.tn_source_name " 
				+ "FROM talk a " 
				+ "JOIN (SELECT it_no, tk_recv_mb_id, MAX(tk_no) latest FROM talk GROUP BY it_no, tk_send_mb_id) b "
				+ "JOIN member c ON a.tk_recv_mb_id = c.mb_id "
				+ "JOIN thumbnail d ON a.tk_recv_mb_id = d.mb_id " 
				+ "WHERE a.tk_send_mb_id = ? AND b.latest = a.tk_no AND a.tk_recv_mb_id <> a.tk_send_mb_id "
				+ "ORDER BY a.tk_send_datetime DESC;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SimpleDateFormat date = new SimpleDateFormat("YY-MM-dd");
				TalkBean list = new TalkBean();
					
				list.setItemNumber(rs.getInt("it_no"));
				list.setRecvMemberID(rs.getString("tk_recv_mb_id"));
				list.setSendMemberID(rs.getString("tk_send_mb_id"));
				list.setSendMemberNick(rs.getString("mb_nick"));
				list.setSendDatetime(rs.getTimestamp("tk_send_datetime"));
				list.setSendDatetimeToString(date.format(list.getSendDatetime()));
				list.setProfileImgName(rs.getString("tn_source_name"));

				// 문자열의 길이가 25자가 넘어갈 경우 최대 25자로 제한
				if(rs.getString("tk_content").length() > 25) { 
					list.setContent(rs.getString("tk_content").substring(0, 25) + "…");
				} else {
					list.setContent(rs.getString("tk_content"));
				}
				
				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}

	// 현재 활성화된 톡의 대화중인 상품의 상품명과 썸네일 가져오기
	public TalkBean getNowItemDetail(int itemNum) {
		TalkBean article = new TalkBean();

		try {
			sql = "SELECT it_name, it_thumbnail FROM item WHERE it_no = ?;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article.setItemName(rs.getString("it_name"));
				article.setItemImgName(rs.getString("it_thumbnail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}
	
	// 현재 활성화된 톡 출품자의 상점 프로필 사진과 소개말 가져오기
	public TalkBean getMarketProfile(String strangerID) {
		TalkBean article = new TalkBean();

		try {
			sql = "SELECT p.mp_introduce, t.tn_source_name, m.mb_nick "
				+ "FROM market_profile p, thumbnail t, member m "
				+ "WHERE m.mb_id = ?;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, strangerID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article.setProfileImgName(rs.getString("t.tn_source_name"));
				article.setSendMemberNick(rs.getString("m.mb_nick"));
				
				if("".equals(rs.getString("mp_introduce"))) {
					article.setMarketIntroduce("상점 소개말이 없습니다.");
				} else { 
					article.setMarketIntroduce(rs.getString("mp_introduce"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}
	
	// 현재 활성화된 톡 출품자의 출품 횟수와 낙찰 횟수 가져오기
	public TalkBean getItemExp(String strangerID) {
		TalkBean article = new TalkBean();

		try {
			sql = "SELECT enter.enter_cnt, successful.successful_cnt "
				+ "FROM (SELECT COUNT(it_no) enter_cnt FROM item WHERE mb_id = ?) enter, "
				+ "     (SELECT COUNT(tr_no) successful_cnt FROM trade WHERE tr_flag = ? AND mb_id = ?) successful";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, strangerID);
			pstmt.setInt(2, 2); // 낙찰 플래그
			pstmt.setString(3, strangerID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article.setEnterCount(rs.getInt("enter_cnt"));
				article.setSuccessfulCount(rs.getInt("successful_cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}
	
	// 현재 활성화된 톡 출품자의 출품중인 상품 가져오기 (최대 4개)
	public ArrayList<TalkBean> getItemExhibiting(String strangerID) {
		ArrayList<TalkBean> articleList = null;

		try {
			articleList = new ArrayList<TalkBean>();
			
			sql = "SELECT it_no, it_name, it_start_price, it_thumbnail FROM item WHERE mb_id = ? ORDER BY it_no DESC LIMIT 4";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, strangerID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TalkBean list = new TalkBean();
					
				list.setItemNumber(rs.getInt("it_no"));
				list.setItemName(rs.getString("it_name"));
				list.setItemPriceStart(rs.getInt("it_start_price"));
				list.setItemImgName(rs.getString("it_thumbnail"));
				
				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	// 현재 활성화된 받은 톡 내용 가져오기
	public ArrayList<TalkBean> getRecvContent(int itemNum, String memberID, String sendMemberID) {
		ArrayList<TalkBean> articleList = null;

		try {
			articleList = new ArrayList<TalkBean>();

			sql = "SELECT tk_send_mb_id, tk_send_datetime, tk_content " 
				+ "FROM talk " 
				+ "WHERE it_no = ? AND tk_recv_mb_id = ? AND tk_send_mb_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			pstmt.setString(2, memberID);
			pstmt.setString(3, sendMemberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SimpleDateFormat date = new SimpleDateFormat("MM-dd hh:mm");
				TalkBean list = new TalkBean();

				list.setSendMemberID(rs.getString("tk_send_mb_id"));
				list.setSendDatetime(rs.getTimestamp("tk_send_datetime"));
				list.setSendDatetimeToString(date.format(list.getSendDatetime()));
				list.setContent(rs.getString("tk_content"));

				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	// 현재 활성화된 보낸 톡 내용 가져오기
	public ArrayList<TalkBean> getSendContent(int itemNum, String memberID, String recvMemberID) {
		ArrayList<TalkBean> articleList = null;

		try {
			articleList = new ArrayList<TalkBean>();

			sql = "SELECT tk_send_mb_id, tk_send_datetime, tk_content " 
				+ "FROM talk " 
				+ "WHERE it_no = ? AND tk_recv_mb_id = ? AND tk_send_mb_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			pstmt.setString(2, recvMemberID);
			pstmt.setString(3, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SimpleDateFormat date = new SimpleDateFormat("MM-dd hh:mm");
				TalkBean list = new TalkBean();

				list.setSendMemberID(rs.getString("tk_send_mb_id"));
				list.setSendDatetime(rs.getTimestamp("tk_send_datetime"));
				list.setSendDatetimeToString(date.format(list.getSendDatetime()));
				list.setContent(rs.getString("tk_content"));

				articleList.add(list);
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
