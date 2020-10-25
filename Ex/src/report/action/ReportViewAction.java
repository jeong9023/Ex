package report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportViewService;
import report.vo.ReportBean;

public class ReportViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int rp_no = Integer.parseInt(request.getParameter("rp_no"));
		
		ReportViewService reportViewService = new ReportViewService();
		ReportBean reportBean = reportViewService.getArticle(rp_no);
		
		request.setAttribute("reportBean", reportBean);
		
		forward= new ActionForward();
		forward.setPath("/report/view.jsp");
		
		return forward;
	}

}
