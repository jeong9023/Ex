package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import shop.svc.ShopTicketService;

public class ShopTicketInquiryAction implements Action {

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
		int ticket = shopTicketService.selectTicket(mb_id);
		if(ticket==0) {
			out.println("티켓이 없어요  ( •́ ̯•̀ )");
			out.close();
			return forward;
		}else {
			out.println(ticket+" 장");
			out.close();
		}
		
		return forward;
	}

}
