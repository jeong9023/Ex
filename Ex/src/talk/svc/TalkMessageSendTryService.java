package talk.svc;

import talk.dao.TalkDAO;
import talk.vo.TalkBean;

import static common.JdbcUtil.*;

import java.sql.Connection;

public class TalkMessageSendTryService {
	public void messageSendTry(TalkBean talkBean) {
		int isSuccess = 0;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.messageSend(talkBean);

		if(isSuccess > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return;
	}
}
