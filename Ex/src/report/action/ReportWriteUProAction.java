package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import report.svc.ReportWriteUProService;
import report.vo.ReportBean;

public class ReportWriteUProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id =(String)session.getAttribute("memberID");
		
		if(mb_id==null) {
				out.println("<script>");
				out.println("alert('로그인이 필요합니다!')");
				out.println("history.back()");
				out.println("</script>");
				out.close();

				return forward;
			}
		
		ReportBean reportBean = new ReportBean();
		reportBean.setMb_id(mb_id);
		reportBean.setRp_suspect_mb_id(request.getParameter("rp_suspect_mb_id"));
		reportBean.setRp_content(request.getParameter("rp_content_div")+"/"+request.getParameter("rp_content"));
		ReportWriteUProService reportWriteUProService = new ReportWriteUProService();
		boolean isWriteSuccess = reportWriteUProService.registReport(reportBean);
		
		if(!isWriteSuccess) {
			out.println("<script>"); 
			out.println("alert('신고 대상자가 등록되지 않은 ID입니다!')"); 
			out.println("history.back()"); 
			out.println("</script>");
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.re");
		}
		
		return forward;
	}

}
