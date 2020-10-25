package report.svc;

import static common.JdbcUtil.*;

import java.sql.Connection;

import report.dao.ReportDAO;
import report.vo.ReportBean;

public class ReportWriteUProService {

	public boolean registReport(ReportBean reportBean) {
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		int insertCount = reportDAO.checkUser(reportBean);
	
		if(insertCount > 0) {
			reportDAO.insertUReport(reportBean);
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
	}
	
}
