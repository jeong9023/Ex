package item.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import item.dao.ItemDAO;
import item.vo.ItemBean;

public class ItemModifyService {

	// 현재 사용자가 글쓴이가 맞는지 (true: 사용자=글쓴이)
	public boolean isWriter(int no, String memberID) { 
		boolean isWriter = false;
		
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		boolean check = itemDAO.isWriter(no, memberID);
		
		if(check) {
			commit(con);
			isWriter = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isWriter;
	}
	
	// 글 데이터 조회
	public ItemBean selectArticle(int no) { 
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		ItemBean itemBean = itemDAO.selectArticle(no);
		
		if(itemBean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return itemBean;
	}
	
	// 글 삭제 되었는지 (1: 삭제)
	public boolean isDelete(int no) { 
		boolean isDelete = false;
		
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		ItemBean check = itemDAO.selectArticle(no);
		
		if(check.getIsDelete() == 1) {
			commit(con);
			isDelete = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDelete;
	}
	
	// 낙찰 또는 유찰 된 상품이라면
	public int isPossible(int no) { 
		int isPossible = 0;
		
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		int check = itemDAO.isPossible(no);
		
		if(check != 0) {
			commit(con);
			isPossible = check;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isPossible;
	}
	
	// 글 수정 요청
	public void tryModify(ItemBean itemBean) { 
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		int article = itemDAO.updateArticle(itemBean);
		
		if(article == 1) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return;
	}
	
}
