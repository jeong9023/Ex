package item.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import item.dao.ItemDAO;
import item.vo.ItemBean;
import member.vo.MemberBean;

public class ItemWriteService {

	// 마켓 오픈 되어있는지 (1: 오픈)
	public int marketIsOpened(String memberID) { 
		int isOpened = 0;
		
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		isOpened = itemDAO.isMarketOpened(memberID);
		
		if(isOpened >= 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isOpened;
	}
	
	// 활동 제한 걸려있는지 (true: 제한)
	public boolean isBan(String memberID) { 
		Connection con = getConnection();
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		boolean isBan = itemDAO.isBan(memberID);
		
		if(isBan) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isBan;
	}
	
	// 땅땅티켓 보유 개수 조회
	public MemberBean selectTicket(String memberID) { 
		Connection con = getConnection();
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		MemberBean searchCount = itemDAO.getTicket(memberID);
		
		if(searchCount != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return searchCount;
	}
	
	// 땅땅티켓 사용
	public int useTicket(String memberID) { 
		Connection con = getConnection();
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		int isUseSuccess = itemDAO.updateTicket(memberID);
		
		if(isUseSuccess > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isUseSuccess;
	}
	
	// 글쓰기 요청
	public boolean registArticle(ItemBean itemBean) { 
		boolean isSuccess = false;
		
		Connection con = getConnection();

		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);
		
		int insertCount = itemDAO.insertArticle(itemBean);
		
		if(insertCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
}
