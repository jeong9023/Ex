package common.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import common.dao.CommonDAO;
import common.vo.ShopRankBean;

public class ShopRankService {
	
	public ArrayList<ShopRankBean> getRank() {
		ArrayList<ShopRankBean> shopRank = null;
		
		Connection con = getConnection();
		CommonDAO commonDAO = CommonDAO.getInstance();
		commonDAO.setConnection(con);
		
		shopRank = commonDAO.getShopRank();
		close(con);
		
		return shopRank;
	}
	
}
