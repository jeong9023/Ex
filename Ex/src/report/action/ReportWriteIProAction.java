package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import report.svc.ReportWriteIProService;
import report.vo.ReportBean;

public class ReportWriteIProAction implements Action{
	
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
		reportBean.setIt_no(Integer.parseInt(request.getParameter("it_no")));
		reportBean.setRp_content(request.getParameter("rp_content_div")+"/"+request.getParameter("rp_content"));
		ReportWriteIProService reportWriteIProService = new ReportWriteIProService();
		boolean isWriteSuccess = reportWriteIProService.registReport(reportBean);
		
		if(!isWriteSuccess) {
			out.println("<script>"); 
			out.println("alert('신고 대상글이 존재하지 않습니다!')"); 
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
