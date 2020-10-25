package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportAjaxPlusService;

public class ReportAjaxAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ReportAjaxPlusService reportAjaxPlusService = new ReportAjaxPlusService();
		int allCount = reportAjaxPlusService.allCountInquiry();
		
		out.println(allCount+" 건");
		out.close();
		
		return forward;
	}

}
