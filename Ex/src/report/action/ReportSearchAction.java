package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportAjaxService;

public class ReportSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String rp_suspect_mb_id = request.getParameter("id");
		
		ReportAjaxService reportAjaxService = new ReportAjaxService();
		int searchCount = reportAjaxService.searchCount(rp_suspect_mb_id);
		
		if(searchCount>0) {
			out.println("신고당한횟수 : "+searchCount+" 건");
			out.close();
		} else {
			out.println("신고내역없음");
			out.close();
		}
		
		return forward;
	}

}
