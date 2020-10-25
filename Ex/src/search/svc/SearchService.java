package search.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.vo.ItemBean;
import search.dao.SearchDAO;
import search.vo.SearchBean;
public class SearchService {
	
	public ArrayList<ItemBean> getArticleList(String keyWord) {
ArrayList<ItemBean> articleList = null;
		
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		SearchDAO searchDAO = SearchDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		searchDAO.setConnection(con);
		
		articleList = searchDAO.getItemList(keyWord);
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		return articleList;
	
	}
	
	public void saveKeyword(String keyWord) {
				
				
				// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
				Connection con = getConnection();
				
				// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
				SearchDAO searchDAO = SearchDAO.getInstance();
				
				// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
				searchDAO.setConnection(con);
				
				int check = searchDAO.checkKeyword(keyWord);
				if(check==1) {
					searchDAO.updateCount(keyWord);
				}else {
					searchDAO.saveKeyword(keyWord);
				}
				
				
					commit(con);
				
				close(con);
				
				
			}
	
	public ArrayList<SearchBean> getPopularKeyword() {
		
		ArrayList<SearchBean> popularKeyword = null;
				
				// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
				Connection con = getConnection();
				
				// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
				SearchDAO searchDAO = SearchDAO.getInstance();
				
				// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
				searchDAO.setConnection(con);
				
				popularKeyword = searchDAO.getPopularKeyword();
				
				
				if(popularKeyword!=null) {
					commit(con);
				}else {
					rollback(con);
				}
				// 공통작업-5. Connection 객체 반환
				close(con);
				
				
				return popularKeyword;
			}
}
