package point.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static common.JdbcUtil.*;

public class PointDAO {
	
	private PointDAO() {}
	private static PointDAO instance;
	public static PointDAO getInstance() {
		if(instance == null) {
			instance = new PointDAO();
		}
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean setPoint(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+1000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}
	public boolean setPointTwo(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+5000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}
	public boolean setPointThree(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+10000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}
	public boolean setPointFour(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+30000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}
	public boolean setPointFive(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+50000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}
	public boolean setPointSix(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "SELECT mb_id, mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point")+100000;
				checkMember = true;
			} else {
				return checkMember;
			}
			close(pstmt);
			sql = "UPDATE member SET mb_point=? WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return checkMember;
	}

	public int getPoint(String mb_id) {
		int point = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT mb_point FROM member WHERE mb_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return point;
	}

	public int getTicket(String mb_id) {
		int ticket=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT mb_ticket FROM member WHERE mb_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ticket = rs.getInt("mb_ticket");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return ticket;
	}

	
}//
