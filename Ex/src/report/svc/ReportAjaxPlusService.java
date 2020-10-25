package report.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import report.dao.ReportDAO;

public class ReportAjaxPlusService {

	public int allCountInquiry() {
		int allCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		allCount=reportDAO.allInquiryCount();
		
		close(con);
		
		return allCount;
	}

	public int defCountInquiry() {
		int defCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		defCount=reportDAO.defInquiryCount();
		
		close(con);
		
		return defCount;
	}

	public int riskCountInquiry() {
		int riskCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		riskCount=reportDAO.riskInquiryCount();
		
		close(con);
		
		return riskCount;
	}

}
