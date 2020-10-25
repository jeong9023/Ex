package member.svc;

import member.dao.MemberDAO;
import member.vo.LoginException;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class MemberLoginProService {
	public boolean isLoginMember(String memberID, String password) throws LoginException {
		boolean isMember = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isMember = memberDAO.selectLoginMember(memberID, password);
		
		close(con);
		
		return isMember;
	}
}
