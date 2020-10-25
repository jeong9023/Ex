package report.svc;

import report.dao.ReportDAO;
import report.vo.ReportBean;
import static common.JdbcUtil.*;

import java.sql.Connection;

public class ReportAjaxService {

	public int countInquiry(ReportBean reportBean) {
		int reCount = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		reCount = reportDAO.inquiryCount(reportBean);
		
		close(con);
		
		return reCount;
	}

	public int searchCount(String rp_suspect_mb_id) {
		int searchCount=0;
		
		Connection con = getConnection();
		
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		searchCount = reportDAO.searchCount(rp_suspect_mb_id);
		
		close(con);
		
		return searchCount;
	}

}
