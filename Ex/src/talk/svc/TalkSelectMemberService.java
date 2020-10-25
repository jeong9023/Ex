package talk.svc;

import talk.dao.TalkDAO;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class TalkSelectMemberService {
	public String getMemberID(int itemNum) {
		String memberID = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		memberID = talkDAO.getMemberID(itemNum);

		if(memberID != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberID;
	}
}
