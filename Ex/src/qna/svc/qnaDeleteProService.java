package qna.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import qna.dao.qnaDAO;



public class qnaDeleteProService {

	// 전달받은 글번호(board_num)를 사용하여 게시물 삭제 요청
	public boolean removeArticle(int wr_id) {
		System.out.println("DeleteProService-removeArticle");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		// 글 삭제하는 deleteArticle
		int deleteCount = qnaDao.deleteArticle(wr_id);
		
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


















