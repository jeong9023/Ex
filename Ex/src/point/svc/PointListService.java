package point.svc;

import java.sql.Connection;

import point.dao.PointDAO;

import static common.JdbcUtil.*;
public class PointListService {

	public int getPoint(String mb_id) {
		int point = 0;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		point = pointDAO.getPoint(mb_id);

		close(con);
		
		return point;
	}

}
