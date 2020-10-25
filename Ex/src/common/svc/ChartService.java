package common.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.Map;

import common.dao.CommonDAO;

public class ChartService {
	Connection con = getConnection();
	CommonDAO commonDAO = CommonDAO.getInstance();
	
	public Map<String, Map<String, Integer>> getChartdata(){
		Map<String, Map<String, Integer>> chartData = null;
	
		commonDAO.setConnection(con);
		chartData = commonDAO.getChartdata();
		
		close(con);
		
		return chartData;
	}
}
