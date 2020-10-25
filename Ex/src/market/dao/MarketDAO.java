package market.dao;
import static common.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Member;

import item.vo.ItemBean;
import market.vo.MarketBean;
import market.vo.MarketBeanForCount;
import market.vo.MarketCommentBean;
import member.dao.MemberDAO;
import member.vo.MemberBean;



public class MarketDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private StringBuffer sb = null;
	private static MarketDAO instance;
	
	private MarketDAO() {};
	public static MarketDAO getInstance() {
		if(instance==null) {
			instance = new MarketDAO();
		}
		return instance;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	//마켓 오픈 여부
	public int isOpened(String mb_id) {
		int isOpened = 0;
		try {
			sql = "SELECT tn_flag FROM thumbnail WHERE mb_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isOpened = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isOpened;
	}
	
	
	public int updateProfile(MarketBean profile) {
		int updateCount=0;
		try {
			sb = new StringBuffer("UPDATE market_profile mp, thumbnail tn ");
			sb.append("SET mp.mp_introduce=?, ");
			sb.append("tn.tn_name =?, tn.tn_source_name = ?, tn.tn_flag=1 ");
			sb.append("WHERE mp.mb_id = tn.mb_id AND mp.mb_id = ?");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, profile.getMp_introduce());
			pstmt.setString(2, profile.getTn_name());
			pstmt.setString(3, profile.getTn_source_name());
			pstmt.setString(4, profile.getMb_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MarketDAO: update오류"+e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	//마켓프로필 불러오기
	public MarketBean selectProfile(String mb_id) {
		MarketBean marketBean = new MarketBean();
		try {
				sb = new StringBuffer("SELECT * FROM ");
				sb.append("market_profile LEFT JOIN thumbnail ");
				sb.append("ON market_profile.mb_id=thumbnail.mb_id ");
				sb.append("WHERE market_profile.mb_id = ?");
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1, mb_id);
				rs = pstmt.executeQuery();
				
					if(rs.next()) {
						marketBean.setMb_id(rs.getString("market_profile.mb_id"));
						marketBean.setMp_introduce(rs.getString("mp_introduce"));
						marketBean.setTn_source_name(rs.getString("tn_source_name"));
					}
			} catch (SQLException e) {
				System.out.println("MarketDAO: select오류"+e.getMessage());
			}finally {
			close(rs);
			close(pstmt);
		}
		return marketBean;
	}
	
	public MarketBeanForCount getCounts(String mb_id) {
		MarketBeanForCount beanCount = new MarketBeanForCount();
		
		try {
			sql = "SELECT COUNT('it_no') FROM item WHERE mb_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				beanCount.setItemcount(rs.getInt(1));
			}
			
			sql = "SELECT COUNT('tr_no') FROM trade WHERE mb_id=? and tr_flag=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				beanCount.setTradecount(rs.getInt(1));
			}
			
			sql = "SELECT mb_point FROM ddang.member WHERE mb_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				beanCount.setPoint(rs.getInt(1));
			}
			
			sql = "SELECT COUNT(*) FROM item WHERE it_no IN(SELECT it_no FROM trade WHERE mb_id=? GROUP BY it_no)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				beanCount.setBiddingcount(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			System.out.println("MarketDAO: getCounts오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return beanCount;
	}
	

	
	public int getItemlistCount(String mb_id) {
		int listCount = 0;
	
		try {
			sql = "SELECT count(it_no) FROM item WHERE mb_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("MarketDAO: getItemlistCount오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<ItemBean> getStoreItemlist(String mb_id, int page, int limit) {
		ArrayList<ItemBean> ItemList = new ArrayList<ItemBean>();
		ItemBean item = null;
		int startRow = (page-1)*limit;
		
		try {
			sql = "SELECT * FROM item WHERE mb_id=? ORDER BY it_no DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				item = new ItemBean();
				
				item.setNo(rs.getInt("it_no"));
				item.setName(rs.getString("it_name"));
				item.setMemberID(rs.getString("mb_id"));
				item.setContent(rs.getString("it_content"));
				item.setThumbnail(rs.getString("it_thumbnail"));
				item.setWriteTime(rs.getTimestamp("it_write_datetime"));
				item.setNowPrice(rs.getInt("it_now_price"));
				item.setDeliveryPrice(rs.getInt("it_delivery_price"));
				item.setCategory(rs.getString("it_category"));
				item.setIsDelete(rs.getInt("it_is_delete"));
				
				ItemList.add(item);
			}
			
		}catch (SQLException e) {
			System.out.println("MarketDAO: getStoreItemlist오류 : "+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return ItemList;
	}
	
	public int getTradeListCount(String mb_id) {
		int listCount = 0;
		try {
			sql = "SELECT count(it_no) FROM trade WHERE mb_id=? and tr_flag=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println("MarketDAO: getTradeListcount오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ItemBean> getTradeList(String mb_id, int page, int limit){
		ArrayList<ItemBean> ItemList = new ArrayList<ItemBean>();
		ItemBean item = null;
		int startRow = (page-1)*limit;
		try {
			sb = new StringBuffer("SELECT * FROM item it JOIN trade tr ");
			sb.append("ON it.it_no = tr.it_no ");
			sb.append("WHERE tr.mb_id = ? and tr_flag=2 ");
			sb.append("ORDER BY it.it_no DESC LIMIT ?,?");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				item = new ItemBean();

				item.setNo(rs.getInt("it_no"));
				item.setName(rs.getString("it_name"));
				item.setMemberID(rs.getString("mb_id"));
				item.setContent(rs.getString("it_content"));
				item.setThumbnail(rs.getString("it_thumbnail"));
				item.setWriteTime(rs.getTimestamp("it_write_datetime"));
				item.setSuccessfulPrice(rs.getInt("it_successful_price"));
				item.setDeliveryPrice(rs.getInt("it_delivery_price"));
				item.setCategory(rs.getString("it_category"));
				item.setIsDelete(rs.getInt("it_is_delete"));
				
				ItemList.add(item);				
			}
		}catch (SQLException e) {
			System.out.println("MarketDAO: getTradeList오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return ItemList;
	}
	
	public int getBiddingListCount(String mb_id) {
		int listCount = 0;
		try {
			sql = "SELECT COUNT(*) FROM item WHERE it_no IN(SELECT it_no FROM trade WHERE mb_id=? GROUP BY it_no)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
			
		}catch (SQLException e) {
			System.out.println("MarketDAO: getTradeListcount오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ItemBean> getBiddingList(String mb_id, int page, int limit){
		ArrayList<ItemBean> ItemList = new ArrayList<ItemBean>();
		ItemBean item = null;
		int startRow = (page-1)*limit;
		try {
			sb = new StringBuffer("SELECT * FROM item WHERE it_no ");
			sb.append("IN(SELECT it_no FROM trade WHERE mb_id=? GROUP BY it_no) ");
			sb.append("ORDER BY it_no DESC LIMIT ?,? ");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				item = new ItemBean();

				item.setNo(rs.getInt("it_no"));
				item.setName(rs.getString("it_name"));
				item.setMemberID(rs.getString("mb_id"));
				item.setContent(rs.getString("it_content"));
				item.setThumbnail(rs.getString("it_thumbnail"));
				item.setWriteTime(rs.getTimestamp("it_write_datetime"));
				item.setNowPrice(rs.getInt("it_now_price"));
				item.setDeliveryPrice(rs.getInt("it_delivery_price"));
				item.setCategory(rs.getString("it_category"));
				item.setIsDelete(rs.getInt("it_is_delete"));
				
				ItemList.add(item);				
			}
		}catch (SQLException e) {
			System.out.println("MarketDAO: getTradeList오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return ItemList;
	}
	
	
	public ArrayList<ItemBean> getRecentViewList(ArrayList<Integer> it_no) {
		ArrayList<ItemBean> ItemList = new ArrayList<ItemBean>();
		ItemBean item = null;
		
		try {
			if(it_no!=null) {
			
				sb = new StringBuffer("SELECT * FROM item WHERE it_no IN(");
			for(int i=0;i<it_no.size()-1;i++) {
				sb.append("?, ");
			}
				sb.append("?)");
				pstmt = con.prepareStatement(sb.toString());
				
				for(int i=0; i<it_no.size(); i++) {
					pstmt.setInt(i+1, it_no.get(i));
				}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				item = new ItemBean();

				item.setNo(rs.getInt("it_no"));
				item.setName(rs.getString("it_name"));
				item.setMemberID(rs.getString("mb_id"));
				item.setContent(rs.getString("it_content"));
				item.setThumbnail(rs.getString("it_thumbnail"));
				item.setWriteTime(rs.getTimestamp("it_write_datetime"));
				item.setNowPrice(rs.getInt("it_now_price"));
				item.setDeliveryPrice(rs.getInt("it_delivery_price"));
				item.setCategory(rs.getString("it_category"));
				item.setIsDelete(rs.getInt("it_is_delete"));
				
				ItemList.add(item);	
			}
			
		}

		}catch (SQLException e) {
			System.out.println("MarketDAO: getRecentViewList오류"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return ItemList;
	}

	
	public boolean PasswordChk(String mb_id, String mb_password) {
		boolean chkResult=false;
		
		try {
			sql = "SELECT mb_no FROM ddang.member WHERE mb_id=? and mb_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chkResult = true;
			}
		} catch (SQLException e) {
			System.out.println("MarketDAO: PasswordChk오류 : "+e.getMessage());
		}finally {
			close(pstmt);
		}
		return chkResult;
	}
	
	public int UpdateMemberInfo(MemberBean memberBean) {
		int updateResult = 0;
		try {
			sb = new StringBuffer("UPDATE ddang.member SET ");
			sb.append("mb_nick=?, mb_password=?, ");
			sb.append("mb_zip=?, mb_address1=?, mb_address2=?, mb_phone=? ");
			sb.append("WHERE mb_id=?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, memberBean.getNick());
			pstmt.setString(2, memberBean.getPassword());
			pstmt.setString(3, memberBean.getZip());
			pstmt.setString(4, memberBean.getAddress1());
			pstmt.setString(5, memberBean.getAddress2());
			pstmt.setString(6, memberBean.getPhone());
			pstmt.setString(7, memberBean.getMemberID());
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("MarketDAO: UpdateMemberInfo오류 : "+e.getMessage());
		}finally {
			close(pstmt);
		}
		return updateResult;
	}
	
	public MemberBean getMemberInfo(String mb_id) {
		MemberBean mb = null;
		try {
			sql = "SELECT * FROM ddang.member WHERE mb_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mb = new MemberBean();
				
				mb.setNo(rs.getInt("mb_no"));
				mb.setMemberID(rs.getString("mb_id"));
				mb.setNick(rs.getString("mb_nick"));
				mb.setZip(rs.getString("mb_zip"));
				mb.setAddress1(rs.getString("mb_address1"));
				mb.setAddress2(rs.getString("mb_address2"));
				mb.setPhone(rs.getString("mb_phone"));
				mb.setEmail(rs.getString("mb_email"));
			}
		}catch (SQLException e) {
			System.out.println("MarketDAO: getMemberInfo오류 : "+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
			return mb;
	}
	
	public int duplicationCheck(String mb_id, String nick){
		/* dupCheck
		 	- 0: 사용가능
		 	- 1: 나의 닉네임(닉네임 수정X)
		 	- 2: 사용불가
		 */
		int dupCheck = -1;
		String db_id = null;
		try {
			sql = "SELECT mb_id FROM ddang.member WHERE mb_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				db_id = rs.getString("mb_id");
				if(mb_id.equals(db_id)){
					dupCheck = 1;
				}else {
					dupCheck = 2;
				}
			}else {
				dupCheck = 0;
			}
		} catch (SQLException e) {
			System.out.println("MarketDAO: duplicationCheck오류 : "+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return dupCheck;
	}
	
}
