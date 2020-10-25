package point.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import point.svc.PointListService;

public class PointListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		
		if(mb_id==null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		PointListService pointListService = new PointListService();
		int point = pointListService.getPoint(mb_id);
		
		if(point>0) {
			out.println(point);
			out.close();
			forward = new ActionForward();
			forward.setPath("/point/main.jsp");
		} else {
			out.println(0);
			out.close();
			forward = new ActionForward();
			forward.setPath("/point/main.jsp");
		}
		
		return forward;
	}

}
