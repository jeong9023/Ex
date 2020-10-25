package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import shop.svc.ShopTicketService;

public class ShopTicketAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id=(String)session.getAttribute("memberID");
		
		if(mb_id==null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("window.close()");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		ShopTicketService shopTicketService = new ShopTicketService();
		boolean ok = shopTicketService.getTicket(mb_id);
		
		if(!ok) {
			out.println("<script>");
			out.println("alert('잔액이 부족하거나 잘못된 경로입니다. ༼◉_◉ ༽ ')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return forward;
		} else if(ok==true){
			out.println("<script>");
			out.println("alert('정상적으로 티켓이 구매되었습니다.감사합니다. ༼ つ ◕_◕ ༽つ  ')");
			out.println("location.href='shop.sp'");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
