package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import report.svc.ReportRestrictService;

public class ReportRestrictAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String mb_id = request.getParameter("mb_id");
		
		ReportRestrictService reportRestrictService = new ReportRestrictService();
		boolean restrict = reportRestrictService.setRestrict(mb_id);
		
		if(!restrict) {
			out.println("<script>");
			out.println("alert('Error')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return forward;
		} else {
			out.println("<script>");
			out.println("alert('정지 정지 정지')");
			out.println("location.href='restrictSup.re'");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
