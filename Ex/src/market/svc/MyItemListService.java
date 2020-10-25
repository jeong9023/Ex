package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.vo.ItemBean;
import market.dao.MarketDAO;

public class MyItemListService {
	MarketDAO marketDAO = MarketDAO.getInstance();

	public int ItemlistCount(String mb_id) {
		int listcount = 0;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		listcount = marketDAO.getItemlistCount(mb_id);
		
		close(con);
		
		return listcount;
	}
	
	public ArrayList<ItemBean> getItemList(String mb_id, int page, int limit) {
		ArrayList<ItemBean> ItemList = null;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		ItemList = marketDAO.getStoreItemlist(mb_id, page, limit);
		
		close(con);
		
		return ItemList; 
	}
	
}
