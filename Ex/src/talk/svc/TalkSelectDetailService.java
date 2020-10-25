package talk.svc;

import talk.dao.TalkDAO;
import talk.vo.TalkBean;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

public class TalkSelectDetailService {
	public TalkBean selectNowItemDetail(int itemNum) {
		TalkBean isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getNowItemDetail(itemNum);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public TalkBean selectMarketProfile(String strangerID) {
		TalkBean isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getMarketProfile(strangerID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public TalkBean selectItemExp(String strangerID) {
		TalkBean isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getItemExp(strangerID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public ArrayList<TalkBean> selectItemExhibiting(String strangerID) {
		ArrayList<TalkBean> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getItemExhibiting(strangerID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public ArrayList<TalkBean> selectRecvContent(int itemNum, String memberID, String sendMemberID) {
		ArrayList<TalkBean> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getRecvContent(itemNum, memberID, sendMemberID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public ArrayList<TalkBean> selectSendContent(int itemNum, String memberID, String recvMemberID) {
		ArrayList<TalkBean> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getSendContent(itemNum, memberID, recvMemberID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
}
