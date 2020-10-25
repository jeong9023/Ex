package market.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import market.svc.UpdateMemberService;
import member.svc.DuplicationEmailCheckProService;
import member.svc.DuplicationIdCheckProService;
import member.svc.DuplicationNickCheckProService;
import member.svc.RegisterProService;
import member.util.SHA256;
import member.vo.MemberBean;

public class UpdateMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		String password = request.getParameter("password");
		MemberBean memberBean = new MemberBean();
		
		memberBean.setMemberID(memberID);
		memberBean.setPassword(request.getParameter("register_password"));
		memberBean.setNick(request.getParameter("register_nick"));
		memberBean.setZip(request.getParameter("register_zip"));
		memberBean.setAddress1(request.getParameter("register_address1"));
		memberBean.setAddress2(request.getParameter("register_address2"));
		memberBean.setPhone(request.getParameter("register_phone"));
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UpdateMemberService service = new UpdateMemberService();
		
		if(service.PasswordChk(memberID, password)) {
			boolean isUpdateSuccess=service.UpdateMemberInfo(memberBean);
			if(isUpdateSuccess) {
				
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("../mypage/MarketDetail.mk");				
			}
		}else {
			out.print("<script type='text/javascript'>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return forward;
		}

		return forward;
	}

}
