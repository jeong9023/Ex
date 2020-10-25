package point.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import point.svc.PointService;

public class PointAddTwoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		String resultCode = request.getParameter("resultCode");
		
		if(resultCode.equals("Fail")) {
			out.println("<script>");
			out.println("alert('취소하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return forward;
		}
		
		if(mb_id == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다!')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		PointService pointService = new PointService();
		boolean checkMember = pointService.checkTwo(mb_id);
		
		if (!checkMember) {
			out.println("<script>");
			out.println("alert('충전 오류 !  ･ᴥ･ ')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('충전 완료 !  •ܫ• ')");
			out.println("location.href='main.po'");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
