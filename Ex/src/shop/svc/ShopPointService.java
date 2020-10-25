package shop.svc;

import java.sql.Connection;

import shop.dao.ShopDAO;

import static common.JdbcUtil.*;
public class ShopPointService {

	public int getPoint(String mb_id) {
		int point = 0;
		Connection con = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(con);
		
		point = shopDAO.getPoint(mb_id);
		
		close(con);
		
		return point;
	}

}
