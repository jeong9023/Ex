package report.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import report.dao.ReportDAO;

public class ReportRestrictClearService {

	public boolean setClear(String mb_id) {
		boolean success = false;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		success = reportDAO.setClear(mb_id);
		
		if(!success) {
			rollback(con);
		}else {
			commit(con);
		}
		close(con);
		
		return success;
	}

}
