package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportRestrictClearService;

public class ReportRestrictClearAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String mb_id = request.getParameter("mb_id");
		ReportRestrictClearService rrcs = new ReportRestrictClearService();
		boolean success = rrcs.setClear(mb_id);
		
		if(!success) {
			out.println("<script>");
			out.println("alert('Error')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return forward;
		} else if(success == true) {
			out.println("<script>");
			out.println("alert('Freedom!!!')");
			out.println("location.href='restrictSup.re'");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
