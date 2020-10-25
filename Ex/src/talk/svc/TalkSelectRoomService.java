package talk.svc;

import talk.dao.TalkDAO;
import talk.vo.TalkBean;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

public class TalkSelectRoomService {
	public ArrayList<TalkBean> roomRecvSelect(String memberID) {
		ArrayList<TalkBean> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getRecvRoom(memberID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public ArrayList<TalkBean> roomSendSelect(String memberID) {
		ArrayList<TalkBean> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getSendRoom(memberID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
}
