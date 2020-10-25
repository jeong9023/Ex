package member.svc;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class GetMemberService {
	// 아이디로 회원정보 가져오기
	public MemberBean getMember(String memberID) {
		MemberBean isSuccess = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		isSuccess = memberDAO.getMember(memberID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
}
