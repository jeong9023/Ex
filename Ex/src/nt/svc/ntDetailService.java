package nt.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import nt.dao.ntDAO;
import nt.vo.ntBean;



public class ntDetailService {

	public ntBean getArticle(int wr_co_id) {
		System.out.println("DetailService - getArticle()");
		
		ntBean article = null;
		
		Connection con = getConnection();
		ntDAO ntDao = ntDAO.getInstance();
		ntDao.setConnection(con);
		
		// 게시물 상세 내용 조회selectArticle
		article = ntDao.selectArticle(wr_co_id);

		close(con);
		
		return article;
	}
	
}


