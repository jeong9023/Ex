package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportDeleteService;

public class ReportDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int rp_no = Integer.parseInt(request.getParameter("rp_no"));
		
		ReportDeleteService reportDeleteService = new ReportDeleteService();
		int successDelete = reportDeleteService.reportDelete(rp_no);
		
		if(successDelete == 1) {
			out.println("<script>");
			out.println("alert('삭제 되었습니다.')");
			out.println("window.close()");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('Error');");
			out.println("history.back()"); 
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
