package report.svc;

import java.sql.Connection;

import report.dao.ReportDAO;

import static common.JdbcUtil.*;

public class ReportRestrictService {

	public boolean setRestrict(String mb_id) {
		boolean restrict = false;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int check = reportDAO.checkMember(mb_id);
		restrict = reportDAO.setRestrict(mb_id); 
		
		if(!restrict) {
			rollback(con);
		}else {
			commit(con);
		}
		close(con);

		return restrict;
	}

}
