package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportAjaxPlusService;

public class ReportAjaxRiskAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ReportAjaxPlusService reportAjaxPlusService = new ReportAjaxPlusService();
		int riskCount = reportAjaxPlusService.riskCountInquiry();
		
		out.println(riskCount+" 건");
		out.close();
		
		return forward;
	}

}
