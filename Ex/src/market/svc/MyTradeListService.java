package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.vo.ItemBean;
import market.dao.MarketDAO;

public class MyTradeListService {
	MarketDAO marketDAO = MarketDAO.getInstance();

	public int TradeListCount(String mb_id) {
		int listcount = 0;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		listcount = marketDAO.getTradeListCount(mb_id);
		
		close(con);
		
		return listcount;
	}
	
	public ArrayList<ItemBean> getTradeList(String mb_id, int page, int limit){
		ArrayList<ItemBean> ItemList = null;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		ItemList = marketDAO.getTradeList(mb_id, page, limit);
		
		close(con);
		
		return ItemList;
	}
}
