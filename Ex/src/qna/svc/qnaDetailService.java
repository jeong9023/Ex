package qna.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import qna.dao.qnaDAO;
import qna.vo.qnaBean;



public class qnaDetailService {

	public qnaBean getArticle(int wr_id) {
		System.out.println("DetailService - getArticle()");
		
		qnaBean article = null;
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		// 게시물 상세 내용 조회selectArticle
		article = qnaDao.selectArticle(wr_id);

		close(con);
		
		return article;
	}
	
	public qnaBean getArticle_re(int wr_id) {
		System.out.println("DetailService - getArticle_re()");
		
		qnaBean article_re = null;
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		// 댓글 상세 내용 조회 selectArticle_re
		article_re = qnaDao.selectArticle_re(wr_id);

		close(con);
		
		return article_re;
	}
	
}


