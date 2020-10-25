package report.svc;

import java.sql.Connection;

import report.dao.ReportDAO;

import static common.JdbcUtil.*;

public class ReportStatsService {

	public boolean statsUpdate(int rp_no, int rp_feedback) {
		boolean statsSuccess = false;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int insertCount = reportDAO.statsUpdate(rp_no, rp_feedback);
		
		if(insertCount > 0) {
			commit(con);
			statsSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return statsSuccess;
	}

	
}
