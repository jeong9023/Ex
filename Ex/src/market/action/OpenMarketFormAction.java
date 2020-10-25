package market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;

public class OpenMarketFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String isOpened = request.getParameter("isOpened");
		
		forward = new ActionForward();
		
		request.setAttribute("isOpened", isOpened);
		forward.setPath("/mypage/open_market.jsp");
		
		return forward;
	}
	
}
