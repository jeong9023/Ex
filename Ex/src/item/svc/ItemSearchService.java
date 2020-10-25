package item.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import item.dao.ItemDAO;
import item.vo.ItemBean;

public class ItemSearchService {

	// 전체 상품 갯수 가져오기
		public int getListCount(String minprice, String maxprice) {
			int listCount = 0;
			
			Connection con = getConnection();
			
			ItemDAO itemDAO = ItemDAO.getInstance();
			itemDAO.setConnection(con);
			
			listCount = itemDAO.selectListCount(minprice, maxprice);
			
			close(con);
			
			return listCount;
		}

		public static ArrayList<ItemBean> getArticleList(int page, int limit, String minprice, String maxprice) {
			ArrayList<ItemBean> articleList = null;
			
			Connection con = getConnection();
			
			ItemDAO itemDAO = ItemDAO.getInstance();
			itemDAO.setConnection(con);
			
			// 페이지 번호(page), 글 갯수(limit) 
			articleList = itemDAO.selectArticleList(page, limit, minprice, maxprice);

			close(con);

			return articleList;
		}
	
}
