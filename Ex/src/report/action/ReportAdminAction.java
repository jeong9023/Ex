package report.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import report.svc.ReportAdminService;
import report.vo.ReportBean;

public class ReportAdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		HttpSession session = request.getSession();
		String mb_id =(String)session.getAttribute("memberID");
		
		if(!mb_id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		
			return forward;
		}
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ReportAdminService reportAdminService = new ReportAdminService();
		int listCount = reportAdminService.getListCount(); 
		ArrayList<ReportBean> reportList = reportAdminService.getReportList(page, limit);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		int startPage =(((int)((double)page / 10 + 0.9)) -1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		
		int statsCount = reportAdminService.getStats();
		int statsTwoCount = 0;
		int statsThreeCount = 0;
		ReportBean reportBean = new ReportBean(statsCount, statsTwoCount, statsThreeCount);

		statsTwoCount = reportAdminService.getStatsTwo();
		reportBean = new ReportBean(statsCount, statsTwoCount, statsThreeCount);
		
		statsThreeCount = reportAdminService.getStatsThreeCount();
		reportBean = new ReportBean(statsCount, statsTwoCount, statsThreeCount);
		
		request.setAttribute("reportBean", reportBean);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("reportList", reportList);
		
		forward = new ActionForward();
		forward.setPath("/report/admin.jsp");
		
		return forward;
	}

}
