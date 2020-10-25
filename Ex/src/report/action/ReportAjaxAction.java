package report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.svc.ReportAjaxService;
import report.vo.ReportBean;

public class ReportAjaxAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ReportBean reportBean = new ReportBean();
		reportBean.setInquiry(request.getParameter("inquiry"));
		
		ReportAjaxService reportAjaxService = new ReportAjaxService();
		int reCount = reportAjaxService.countInquiry(reportBean);
		
		if(reCount>0) {
			out.println("'"+reportBean.getInquiry()+"' 으로 조회된 피해사례 : "+reCount+" 건");
			out.close();
		}else {
			out.println("피해사례없음");
			out.close();
		}
		return forward;
	}

}
