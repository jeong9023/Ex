package member.svc;

import member.dao.MemberDAO;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class SetMemberService {
	// 이메일 인증 확인 후 암호화 컬럼 초기화
	public void setMemberEmailConfirm(String memberID) {
		int isSuccess = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		isSuccess = memberDAO.setMemberEmailConfirm(memberID);

		if(isSuccess == 1) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return;
	}
}
