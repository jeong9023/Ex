package member.svc;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class RegisterProService {
	public int tryInsertMember(MemberBean memberBean) {
		int isSuccess = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		isSuccess = memberDAO.insertMember(memberBean);

		if(isSuccess == 1) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
}
