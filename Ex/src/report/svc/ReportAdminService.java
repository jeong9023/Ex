package report.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;

import report.dao.ReportDAO;
import report.vo.ReportBean;
public class ReportAdminService {

	public int getListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		listCount = reportDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<ReportBean> getReportList(int page, int limit) {
		ArrayList<ReportBean> reportList = null;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		reportList = reportDAO.selectReportList(page, limit);
		
		close(con);
		
		return reportList;
	}

	public int getStats() {
		int statsCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int resultCount = reportDAO.resultCount();
		int stayCount = reportDAO.stayCount();
		int oneR = resultCount / 10;
		int twoR = resultCount - stayCount;
		statsCount = twoR / oneR ;
		
		close(con);
		
		return statsCount;
	}

	public int getStatsTwo() {
		int statsTwoCount = 0;
		
		DecimalFormat form = new DecimalFormat("#.#");
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int resultCount = reportDAO.resultCount();
		int statsFlag = reportDAO.getFlag();
		double cal=((double)statsFlag/(double)resultCount);
		form.format(cal); 
		double cal02 = cal*10.0;
		statsTwoCount = (int)cal02;
		
		close(con);
	
		return statsTwoCount;
	}

	public int getStatsThreeCount() {
		int statsThreeCount = 0;
		
		DecimalFormat form = new DecimalFormat("#.#");
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int resultCount = reportDAO.resultCount();
		int crimeCount = reportDAO.getCrime();
		double cal=((double)crimeCount/(double)resultCount);
		form.format(cal); 
		double cal02 = cal*10.0;
		statsThreeCount = (int)cal02;
		
		close(con);
		
		return statsThreeCount;
	}
		

}
