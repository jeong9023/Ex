package item.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import item.dao.ItemDAO;
import item.vo.ItemBean;
import order.vo.orderBean;

public class ItemViewService {

	public ItemBean getArticle(int no) {

//			System.out.println("BoardDetailService - getArticle()");
//			System.out.println("board_num = " + board_num);

		ItemBean article = null;

		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		article = itemDAO.selectArticle(no);

//			if(article != null) {
//				int updateCount = itemDAO.updateReadcount(no);
//				if(updateCount > 0) {
//					commit(con);
//				} else {
//					rollback(con);
//				}
//			}

		close(con);

		return article;

	}

	public int getOrderArticle(int no) {
		int orderarticle = 0;

		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		orderarticle = itemDAO.selectOrderArticle(no);


		close(con);

		return orderarticle;
	}
	
	

}
