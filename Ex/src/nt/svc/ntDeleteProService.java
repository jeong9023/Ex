package nt.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import nt.dao.ntDAO;



public class ntDeleteProService {

	// 전달받은 글번호(board_num)를 사용하여 게시물 삭제 요청
	public boolean removeArticle(int wr_co_id) {
		System.out.println("DeleteProService-removeArticle");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		ntDAO ntDao = ntDAO.getInstance();
		ntDao.setConnection(con);
		
		// 글 삭제하는 deleteArticle
		int deleteCount = ntDao.deleteArticle(wr_co_id);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}
	
}


















