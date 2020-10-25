package qna.svc;

import java.sql.Connection;

import qna.dao.qnaDAO;
import qna.vo.qnaBean;

// static import 기능을 사용하여 db 패키지의 JdbcUtil 클래스명 내에 있는 메서드를 지정시
// 클래스명 없이 바로 메서드를 호출할 수 있다!
// => db.JdbcUtil.getConnection; 이라고 지정하면 getConnection() 메서드만 호출 가능하지만
//    db.JdbcUtil.*; 형태로 지정하면 JdbcUtil 클래스의 모든 static 메서드를 호출 가능
import static common.JdbcUtil.*;

// Action 클래스로부터 요청받은 작업을 DAO 클래스를 사용하여 처리하고 그 결과를 리턴하는 클래스
public class qnaWriteProService {

	public boolean registArticle(qnaBean qb) { 
		System.out.println("WriteProService - registArticle()");
		boolean isWriteSucces = false; // 글 등록 성공 여부를 리턴할 변수
		
		Connection con = getConnection();
		qnaDAO qnaDao = qnaDAO.getInstance();
		qnaDao.setConnection(con);
		
		//글 등록 메서드insertArticle
		int insertCount = qnaDao.insertArticle(qb);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSucces = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isWriteSucces;
	}
	
}













