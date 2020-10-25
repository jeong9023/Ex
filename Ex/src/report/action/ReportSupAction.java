package report.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import report.svc.ReportListService;
import report.vo.ReportBean;

public class ReportSupAction implements Action {

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
			out.println("window.close()");
			out.println("</script>");
			out.close();

			return forward;
		}
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ReportListService reportListService = new ReportListService();
		int listCount = reportListService.getSupListCount(); 
		
		ArrayList<ReportBean> supList = reportListService.getSupList(page, limit);
		int maxPage = (int)((double)listCount / limit + 0.95);
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("supList", supList);
		
		forward = new ActionForward();
		forward.setPath("/report/restrict.jsp");
		
		return forward;
	}

}
