package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportStatsService;

public class ReportStatsProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("rp_feedback")==null) {
			out.println("<script>");
			out.println("alert('처리 선택 안함!')");
			out.println("history.back()");
			out.println("</script>");
			
			out.close();
		}
		
		int rp_no = Integer.parseInt(request.getParameter("rp_no"));
		int rp_feedback = Integer.parseInt(request.getParameter("rp_feedback"));

		ReportStatsService reportStatsService = new ReportStatsService();
		boolean statsSuccess=reportStatsService.statsUpdate(rp_no, rp_feedback);
		
		if(!statsSuccess) {
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			forward = new ActionForward();
			forward.setPath("/report/admin.re");
		}
		
		return forward;
	}

}
