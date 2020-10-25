package point.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import point.dao.PointDAO;

public class TicketListService {

	public int getTicket(String mb_id) {
		int ticket = 0;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		ticket = pointDAO.getTicket(mb_id);
		
		close(con);
		
		return ticket;
	}

}
