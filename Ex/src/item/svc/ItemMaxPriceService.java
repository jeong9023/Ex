package item.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;

import item.dao.ItemDAO;

public class ItemMaxPriceService {
	

public void getMaxPrice(int maxPrice, String customerID, int it_no, int nowPrice, int price) {
		
		Connection con = getConnection(); 
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		itemDAO.maxPrice(it_no, maxPrice, customerID, nowPrice, price);

		
		commit(con);
		
			
		close(con);

}

}
