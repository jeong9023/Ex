package report.svc;

import java.sql.Connection;

import report.dao.ReportDAO;

import static common.JdbcUtil.*;

public class ReportDeleteService {

	public int reportDelete(int rp_no) {
		int successDelete = 0;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int reportDelete = reportDAO.reportDelete(rp_no);
		
		if(reportDelete > 0) {
			commit(con);
			successDelete = 1;
		}else {
			rollback(con);
		}
		close(con);
		
		return successDelete;
	}

}
