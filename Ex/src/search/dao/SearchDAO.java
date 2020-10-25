package search.dao;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import item.vo.ItemBean;
import order.vo.orderBean;
import search.vo.SearchBean;

public class SearchDAO {
	private SearchDAO() {}
	
	private static SearchDAO instance;
	
	public static SearchDAO getInstance() {
		if(instance == null) {
			instance = new SearchDAO();
		}
		return instance;
	}
	
	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public ArrayList<ItemBean> getItemList(String keyWord) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ItemBean> articleList = new ArrayList<ItemBean>();
		try {
			String sql="select * from item where it_name like ? order by it_write_datetime desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyWord+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
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
				article.setDeliveryPrice(rs.getInt("it_delivery_price"));
				article.setCategory(rs.getString("it_category"));
				article.setIsDelete(rs.getInt("it_is_delete"));
				
				articleList.add(article);
			}
		}catch (Exception e) {
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return articleList;
	}
	
	public void saveKeyword(String keyWord) {
		PreparedStatement pstmt=null;
		try {
			String sql="insert into keyword (kw_keyword, kw_search_count) values (?,1)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	}
	
	public int checkKeyword(String keyWord) {
		int num = -1;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select kw_keyword from keyword where kw_keyword =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			rs=pstmt.executeQuery();
		if(rs.next()) {
				num = 1;
			}
		}catch (Exception e) {
				
			}finally {
				close(pstmt);
				close(rs);
			}
		return num;
	
}
	
	public void updateCount(String keyWord) {
		
		PreparedStatement pstmt=null;
		
		try {
			String sql="update keyword set kw_search_count =kw_search_count+1 where kw_keyword =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			pstmt.executeUpdate();
		
		}catch (Exception e) {
				
			}finally {
				close(pstmt);
			}
		
	
}
	
	
public ArrayList<SearchBean> getPopularKeyword() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SearchBean> popularKeyword = new ArrayList<SearchBean>();
		try {
			String sql="select kw_keyword, kw_search_count from keyword order by kw_search_count desc limit 10";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SearchBean pkList = new SearchBean();
				pkList.setKeyWord(rs.getString("kw_keyword"));
				
				
				popularKeyword.add(pkList);
			}
		}catch (Exception e) {
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return popularKeyword;
	}
}

