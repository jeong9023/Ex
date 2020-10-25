package market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import market.svc.UpdateMemberService;
import member.vo.MemberBean;

public class UpdateMemberFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		UpdateMemberService service = new UpdateMemberService();
		MemberBean memberBean = service.getMemberInfo(memberID);
		
		request.setAttribute("mb", memberBean);
		forward = new ActionForward();
		forward.setPath("/mypage/updateMemberInfo.jsp");

		return forward;
	}

}
