package report.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import report.dao.ReportDAO;
import report.vo.ReportBean;
public class ReportListService {

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

	public ArrayList<ReportBean> getMyList(int page, int limit, String mb_id) {
		ArrayList<ReportBean> reportList = null;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		reportList = reportDAO.selectMyList(page, limit, mb_id);
		
		close(con);
		
		return reportList;
	}

	public int getMyListCount(String mb_id) {
		int listCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		listCount = reportDAO.selectMyListCount(mb_id);
		
		close(con);
		
		return listCount;
	}

	public int getSupListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		listCount = reportDAO.selectSupCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ReportBean> getSupList(int page, int limit) {
		ArrayList<ReportBean> supList = null;
		
		Connection con = getConnection();
		
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		supList = reportDAO.selectSupList(page, limit);
		
		close(con);
		
		return supList;
	}

		

}
