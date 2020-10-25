package item.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.dao.ItemDAO;
import item.vo.ItemBean;

public class ItemListService {

	// 전체 상품 갯수 가져오기
	public int getListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		listCount = itemDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	public static ArrayList<ItemBean> getArticleList(int page, int limit) {
		ArrayList<ItemBean> articleList = null;
		
		Connection con = getConnection();
		
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		// 페이지 번호(page), 글 갯수(limit) 
		articleList = itemDAO.selectArticleList(page, limit);

		close(con);

		return articleList;
	}
	
}
