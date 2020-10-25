package qna.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import qna.dao.qnaDAO;
import qna.vo.qnaBean;


public class qnaModifyProService {

	// 글 수정 작업
	public boolean modifyArticle(qnaBean article) {
		System.out.println("ModifyProService - modifyArticle()");
		boolean isModifySuccess = true;
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		// 게시물 수정 updateArticle
		int updateCount = qnaDao.updateArticle(article);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		
		
		close(con);
		
		return isModifySuccess;
	}

}
















