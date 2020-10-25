package member.svc;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class DuplicationIdCheckProService {
	public MemberBean isDuplicationId(String registerID) {
		MemberBean isExist = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isExist = memberDAO.getMember(registerID);

		if(isExist != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isExist;
	}
}
