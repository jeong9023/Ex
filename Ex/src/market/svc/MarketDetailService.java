package market.svc;
import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import market.dao.MarketDAO;
import market.vo.MarketBean;
import market.vo.MarketBeanForCount;

public class MarketDetailService {
	MarketDAO marketDAO = MarketDAO.getInstance();
	
	public int isOpened(String mb_id) {
		int isOpened = 0;
		Connection con = getConnection();
		marketDAO.setConnection(con);
		isOpened = marketDAO.isOpened(mb_id);
		
		commit(con);
		close(con);
		
		return isOpened;
	}
	
	public MarketBean getMarketProfile(String mb_id) {
		MarketBean marketBean = null;
		Connection con = getConnection();
		marketDAO.setConnection(con);
		marketBean = marketDAO.selectProfile(mb_id);
		if(marketBean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
			close(con);
		return marketBean;
	}
	
	public MarketBeanForCount getCounts(String mb_id) {
		MarketBeanForCount counts = new MarketBeanForCount();
		Connection con = getConnection();
		marketDAO.setConnection(con);
		counts = marketDAO.getCounts(mb_id);
		if(counts!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
			
		return counts;
	}



}
