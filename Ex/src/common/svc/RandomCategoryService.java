package common.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import common.dao.CommonDAO;
import common.vo.RandomCategoryBean;

public class RandomCategoryService {

	public ArrayList<RandomCategoryBean> getRandomCategory() { 
		Connection con = getConnection();
		CommonDAO commonDAO = CommonDAO.getInstance();
		commonDAO.setConnection(con);
		
		ArrayList<RandomCategoryBean> isSuccess = commonDAO.getRandomCategory();
		
		if(isSuccess.size() > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	
}
