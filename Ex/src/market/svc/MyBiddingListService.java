package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import item.vo.ItemBean;
import market.dao.MarketDAO;

public class MyBiddingListService {
	MarketDAO marketDAO = MarketDAO.getInstance();

	public int BiddingListCount(String mb_id) {
		int listcount = 0;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		listcount = marketDAO.getBiddingListCount(mb_id);
		
		close(con);
		
		return listcount;
	}
	
	public ArrayList<ItemBean> getBiddingList(String mb_id, int page, int limit){
		ArrayList<ItemBean> ItemList = null;
		
		Connection con = getConnection();
		marketDAO.setConnection(con);
		ItemList = marketDAO.getBiddingList(mb_id, page, limit);
		
		close(con);
		
		return ItemList;
	}
}
