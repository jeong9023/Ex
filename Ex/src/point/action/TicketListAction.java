package point.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import point.svc.PointListService;
import point.svc.TicketListService;

public class TicketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		
		TicketListService ticketListService = new TicketListService();
		int ticket = ticketListService.getTicket(mb_id);
		
		if(ticket>0) {
			out.println(ticket);
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
