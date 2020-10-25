package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.svc.ItemSuccessfulService;
import item.svc.ItemViewService;
import item.vo.ItemBean;
import talk.svc.TalkSelectMemberService;

public class ItemSuccessfulAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		
		// itemSuccessfulService
		int it_no = Integer.parseInt(request.getParameter("it_no"));
		String memberID = request.getParameter("memberID");
		HttpSession session = request.getSession();
		String customerID = (String) session.getAttribute("memberID");
		request.setAttribute("customerID", customerID);
		String endTime = request.getParameter("endTime");

		ItemSuccessfulService itemSuccessfulService = new ItemSuccessfulService();
		itemSuccessfulService.getSuccessful(memberID, customerID, it_no, endTime);

//		forward = new ActionForward();
//		forward.setPath("/item/view.jsp");

		return forward;
	}
}
