package market.svc;
import static common.JdbcUtil.*;
import java.sql.Connection;

import market.dao.MarketDAO;
import market.vo.MarketBean;



public class OpenMarketProService {

	public boolean UpdateProfile(MarketBean profile) {
		boolean isUpdatedSuccess = false;
		Connection con = getConnection();
		
		MarketDAO marketDAO = MarketDAO.getInstance();
		marketDAO.setConnection(con);
		
		int updateCount = marketDAO.updateProfile(profile);
		
		if(updateCount>1) {
			commit(con);
			isUpdatedSuccess = true;
		}else {
			rollback(con);
		}
			close(con);
		return isUpdatedSuccess;
	}
}
