package shop.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;

import shop.dao.ShopDAO;

public class ShopTicketService {

	public boolean getTicket(String mb_id) {
		boolean ok = false;
		int ticket = 0;
		
		Connection con = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(con);
		
		ticket = shopDAO.checkMember(mb_id);
		
		if(ticket==97979797) {
			rollback(con);
		} else {
			int point=shopDAO.getPoint(mb_id);
			point -= 9900;
			ok = shopDAO.setTicket(mb_id, point, ticket);
			commit(con);
		}
		
		close(con);
		
		return ok;
	}

	public int selectTicket(String mb_id) {
		int ticket = 0;
		
		Connection con = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(con);
		
		boolean checkMember=shopDAO.checkTicket(mb_id);
		
		if(!checkMember) {
			return ticket;
		} else {
			ticket = shopDAO.selectTicket(mb_id);
		}
		
		close(con);
		
		return ticket;
	}

}
