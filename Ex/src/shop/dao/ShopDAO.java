package shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.JdbcUtil.*;

public class ShopDAO {
	private ShopDAO() {}
	private static ShopDAO instance;
	public static ShopDAO getInstance() {
		if(instance == null) {
			instance = new ShopDAO();
		}
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getPoint(String mb_id) {
		int point = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT mb_point FROM member WHERE mb_id =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt("mb_point");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return point;
	}
	
	public int checkMember(String mb_id) {
		int ticket = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="SELECT mb_id, mb_point, mb_ticket FROM member WHERE mb_id=? && mb_point>=10000";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ticket=rs.getInt("mb_ticket");
			}else {
				ticket=97979797;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return ticket;
	}
	
	public boolean setTicket(String mb_id, int point, int ticket) {
		boolean ok = true;
		PreparedStatement pstmt = null;
		try {
			String sql= "UPDATE member SET mb_ticket=?, mb_point=? WHERE mb_id=?";           
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ticket+1);
			pstmt.setInt(2, point);
			pstmt.setString(3, mb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return ok;
	}
	
	public boolean checkTicket(String mb_id) {
		boolean checkMember = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql= "SELECT mb_id FROM member WHERE mb_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				checkMember=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return checkMember;
	}
	
	public int selectTicket(String mb_id) {
		int ticket = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT mb_id, mb_ticket FROM member WHERE mb_id=?";
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
	
	
	
	
}
