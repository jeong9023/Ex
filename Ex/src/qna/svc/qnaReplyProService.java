package qna.svc;

import java.sql.Connection;

import qna.dao.qnaDAO;
import qna.vo.qnaBean;

import static common.JdbcUtil.*;

public class qnaReplyProService {

	// 답글 등록하기
	public boolean replyArticle(qnaBean article_re) {
		System.out.println("ReplyProService - replyArticle");
		boolean isReplySuccess = false;
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		// 답변 글 등록updateReArticle
		int insertCount = qnaDao.updateReArticle(article_re);
		
		if(insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isReplySuccess;
	}

}

















