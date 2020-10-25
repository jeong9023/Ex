package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;

import market.dao.MarketDAO;
import member.vo.MemberBean;

public class UpdateMemberService {
	MarketDAO marketDAO = MarketDAO.getInstance();
	Connection con = getConnection();

	public boolean PasswordChk(String mb_id, String mb_password) {
		boolean chkResult = false;
		
		marketDAO.setConnection(con);
		chkResult = marketDAO.PasswordChk(mb_id, mb_password);
		
		return chkResult;
	}

	public boolean UpdateMemberInfo(MemberBean memberBean) {
		boolean isUpdateSuccess = false;
		
		marketDAO.setConnection(con);
		int updateResult = marketDAO.UpdateMemberInfo(memberBean);
		
		if(updateResult>0) {
			commit(con);
			isUpdateSuccess = true;
		}else {
			rollback(con);
		}
		return isUpdateSuccess;
	}
	
	public MemberBean getMemberInfo(String mb_id) {
		MemberBean mb = null;
		
		marketDAO.setConnection(con);
		mb = marketDAO.getMemberInfo(mb_id);
		return mb;
	}
	
	public int duplicationCheck(String mb_id, String nick) {
		int dupCheck = -1;
		
		marketDAO.setConnection(con);
		dupCheck = marketDAO.duplicationCheck(mb_id, nick);
		
		return dupCheck;
	}

}
