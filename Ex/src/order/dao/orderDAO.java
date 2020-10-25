package order.dao;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import order.vo.orderBean;

public class orderDAO {
	private orderDAO() {}
	
	private static orderDAO instance;
	
	public static orderDAO getInstance() {
		if(instance == null) {
			instance = new orderDAO();
		}
		return instance;
	}
	
	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<orderBean> selectOrderList(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<orderBean> articleList = new ArrayList<orderBean>();

		
		try {
		
			String sql = "SELECT it.it_category, tr.tr_datetime, tr.mb_id, tr.tr_flag, it.it_successful_price, it.it_name, it.mb_id as mb_id2, it.it_thumbnail, it.it_now_price, it.it_no";
			sql += " FROM trade AS tr";
			sql += " JOIN item AS it";
			sql += " ON it.it_no = tr.it_no";
			sql += " WHERE tr.mb_id =?";
			sql += " order by tr_datetime desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				orderBean article = new orderBean();
				article.setIt_category(rs.getString("it_category"));
//				article.setTr_datetime(rs.getTimestamp("tr_datetime"));
				article.setMb_id(rs.getString("mb_id"));
				article.setIt_no(rs.getInt("it_no"));
				
//				article.setIt_successful_price(rs.getInt("it_successful_price"));
				article.setIt_name(rs.getString("it_name"));
				article.setMb_id2(rs.getString("mb_id2"));
				article.setIt_thumbnail(rs.getString("it_thumbnail"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String date = dateFormat.format(rs.getTimestamp("tr_datetime"));
//				
				article.setTr_datetime(date);
				
				if(rs.getInt("tr_flag")==1) {
					article.setTr_flag("입찰중");
					article.setIt_successful_price(rs.getInt("it_now_price"));
				}else {
					article.setTr_flag("낙찰");
					article.setIt_successful_price(rs.getInt("it_successful_price"));
				}
				
				
				
				articleList.add(article);
				
				
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("orderDAO - selectArticleList() 실패! : " + e.getMessage());
		} finally {
			
			close(pstmt);
			close(rs);
		}
		
		return articleList;
	}
	

}
