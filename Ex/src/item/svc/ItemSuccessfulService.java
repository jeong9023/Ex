package item.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;

import item.dao.ItemDAO;

public class ItemSuccessfulService {

	public void getSuccessful(String memberID, String customerID, int it_no, String endTime) {
		
		Connection con = getConnection(); 
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		itemDAO.successful(it_no, memberID, customerID, endTime);

		
		commit(con);
		
			
		close(con);
		
	}

}
