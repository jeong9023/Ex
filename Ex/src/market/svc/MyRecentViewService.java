package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.vo.ItemBean;
import market.dao.MarketDAO;

public class MyRecentViewService {

	public ArrayList<ItemBean> getRecentView(ArrayList<Integer> it_no) {
		ArrayList<ItemBean> ItemList = null;
		
		Connection con = getConnection();
		MarketDAO marketDAO = MarketDAO.getInstance();
		marketDAO.setConnection(con);
		
		ItemList = marketDAO.getRecentViewList(it_no);
		close(con);
		
		return ItemList;
	}

}
