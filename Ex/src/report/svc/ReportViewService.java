package report.svc;

import java.sql.Connection;

import report.dao.ReportDAO;
import report.vo.ReportBean;
import static common.JdbcUtil.*;
public class ReportViewService {

	public ReportBean getArticle(int rp_no) {
		ReportBean reportBean = null;
		
		Connection con = getConnection();
		ReportDAO reportDAO = ReportDAO.getInstance();
		reportDAO.setConnection(con);
		
		reportBean = reportDAO.selectArticle(rp_no);
		
		close(con);
		
		return reportBean;
	}

}
