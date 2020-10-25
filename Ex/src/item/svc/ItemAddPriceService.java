package item.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;
import static common.JdbcUtil.rollback;

import java.sql.Connection;

import item.dao.ItemDAO;
import item.vo.ItemBean;

public class ItemAddPriceService {

	public int getAddPrice(int it_no, int price) {
		
		int addPrice = 0;
		
		Connection con = getConnection(); 
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		addPrice = itemDAO.addPrice(it_no, price);

		
		commit(con);
		
			
		close(con);

		return addPrice;
		
	}

	public void getAdduser(int it_no, String customerID) {
		
		Connection con = getConnection(); 
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		itemDAO.addUser(it_no, customerID);

		
		commit(con);
		
			
		close(con);

		
	}
}
