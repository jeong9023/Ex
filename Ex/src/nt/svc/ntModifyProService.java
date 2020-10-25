package nt.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import nt.dao.ntDAO;
import nt.vo.ntBean;


public class ntModifyProService {

	// 글 수정 작업
	public boolean modifyArticle(ntBean article) {
		System.out.println("ModifyProService - modifyArticle()");
		boolean isModifySuccess = true;
		
		Connection con = getConnection();
		ntDAO ntDao = ntDAO.getInstance();
		ntDao.setConnection(con);
		
		// 게시물 수정 updateArticle
		int updateCount = ntDao.updateArticle(article);
		
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
















