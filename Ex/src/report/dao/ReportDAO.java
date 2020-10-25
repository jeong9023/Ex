package report.dao;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import report.vo.ReportBean;

public class ReportDAO {

	private ReportDAO() {}
	private static ReportDAO instance;
	public static ReportDAO getInstance() {
		if(instance == null) {
			instance = new ReportDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int insertUReport(ReportBean reportBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO report (rp_no, mb_id, rp_suspect_mb_id, rp_content,rp_flag,rp_feedback) VALUES (null,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reportBean.getMb_id());
			pstmt.setString(2, reportBean.getRp_suspect_mb_id());
			pstmt.setString(3, reportBean.getRp_content());
			pstmt.setInt(4, 2);
			pstmt.setInt(5, 0);
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
	
		return insertCount;
	}

	public int insertIReport(ReportBean reportBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO report (rp_no, mb_id,rp_content,rp_flag,rp_feedback,it_no) VALUES (null,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reportBean.getMb_id());
			pstmt.setString(2, reportBean.getRp_content());
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, reportBean.getIt_no());
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
	
		return insertCount;
	}

	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(rp_no) FROM report";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
	
	public int selectMyListCount(String mb_id) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(rp_no) FROM report WHERE mb_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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

	public ArrayList<ReportBean> selectReportList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page - 1) * limit;
		ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		
		try {
			String sql = "SELECT * FROM report ORDER BY rp_feedback = 0 DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportBean list = new ReportBean();
				list.setRp_no(rs.getInt("rp_no"));
				list.setMb_id(rs.getString("mb_id"));
				list.setRp_suspect_mb_id(rs.getString("rp_suspect_mb_id"));
				list.setRp_content(rs.getString("rp_content"));
				list.setRp_flag(rs.getInt("rp_flag"));
				list.setRp_feedback(rs.getInt("rp_feedback"));
				list.setIt_no(rs.getInt("it_no"));
				reportList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reportList;
	}

	public int inquiryCount(ReportBean reportBean) {
		int reCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT rp_feedback FROM report WHERE rp_suspect_mb_id=? && rp_feedback=2";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reportBean.getInquiry());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				reCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reCount;
	}

	public int allInquiryCount() {
		int allCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				allCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return allCount;
	}

	public int defInquiryCount() {
		int defCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report WHERE rp_feedback=2 || rp_feedback=3";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				defCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return defCount;
	}

	public int riskInquiryCount() {
		int riskCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report WHERE rp_feedback=2 || rp_feedback=3 || rp_feedback=4";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				riskCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return riskCount;
	}

	public int resultCount() {
		int resultCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				resultCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return resultCount;
	}

	public int stayCount() {
		int stayCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report WHERE rp_feedback = 0";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				stayCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return stayCount;
	}

	public int getFlag() {
		int statsTwoCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_flag FROM report WHERE rp_flag=2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				statsTwoCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return statsTwoCount;
	}

	public int getCrime() {
		int statsThreeCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_feedback FROM report WHERE rp_feedback=2 || rp_feedback=3";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				statsThreeCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return statsThreeCount;
	}

	public ArrayList<ReportBean> selectMyList(int page, int limit, String mb_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow = (page - 1) * limit;
		ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		try {
			String sql ="SELECT rp_no, mb_id, rp_suspect_mb_id, it_no, SUBSTRING_INDEX(rp_content,'/',1) as 'rp_content', rp_feedback FROM report WHERE mb_id=? LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportBean list = new ReportBean();
				list.setRp_no(rs.getInt("rp_no"));
				list.setRp_suspect_mb_id(rs.getString("rp_suspect_mb_id"));
				list.setIt_no(rs.getInt("it_no"));
				list.setRp_content(rs.getString("rp_content"));
				list.setRp_feedback(rs.getInt("rp_feedback"));
				reportList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reportList;
	}

	public ReportBean selectArticle(int rp_no) {
		ReportBean article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM report WHERE rp_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rp_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new ReportBean();
				article.setRp_no(rs.getInt("rp_no"));
				article.setMb_id(rs.getString("mb_id"));
				article.setRp_suspect_mb_id(rs.getString("rp_suspect_mb_id"));
				article.setRp_content(rs.getString("rp_content"));
				article.setRp_flag(rs.getInt("rp_flag"));
				article.setRp_feedback(rs.getInt("rp_feedback"));
				article.setIt_no(rs.getInt("it_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}

	public int statsUpdate(int rp_no, int rp_feedback) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql="UPDATE report SET rp_feedback=? WHERE rp_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rp_feedback);
			pstmt.setInt(2, rp_no);
			insertCount = 1;
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int checkUser(ReportBean reportBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT mb_id FROM member WHERE mb_id = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reportBean.getRp_suspect_mb_id());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				insertCount = 1;
			}else {
				insertCount = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return insertCount;
	}

	public int checkItem(ReportBean reportBean) {
		int insertCount =0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="SELECT it_no FROM item WHERE it_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reportBean.getIt_no());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				insertCount = 1;
			} else {
				insertCount = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return insertCount;
	}

	public int searchCount(String rp_suspect_mb_id) {
		int searchCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT rp_suspect_mb_id, it_no FROM report WHERE rp_suspect_mb_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rp_suspect_mb_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				searchCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return searchCount;
	}

	public int reportDelete(int rp_no) {
		int reportDelete = 1;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM report WHERE rp_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rp_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return reportDelete;
	}
	
	public boolean setRestrict(String mb_id) {
		boolean restrict = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="UPDATE member SET mb_ban_datetime=now() WHERE mb_id =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.executeUpdate();
			restrict = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return restrict;
	}

	public int checkMember(String rp_suspect_mb_id) {
		int check = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="SELECT mb_id FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rp_suspect_mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				check = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return check;
	}

	public int selectSupCount() {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT mb_id FROM member";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return count;
	}

	public ArrayList<ReportBean> selectSupList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow = (page - 1) * limit;
		ArrayList<ReportBean> supList = new ArrayList<ReportBean>();
		try {
			String sql ="SELECT * FROM member LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportBean rList = new ReportBean();
				rList.setMb_id(rs.getString("mb_id"));
				rList.setMb_ban_datetime(rs.getTimestamp("mb_ban_datetime"));
				supList.add(rList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return supList;
	}

	public boolean setClear(String mb_id) {
		boolean success = false;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE member SET mb_ban_datetime=null WHERE mb_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return success;
	}

	
	
	
}




