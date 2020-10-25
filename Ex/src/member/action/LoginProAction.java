package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import member.svc.GetMemberService;
import member.svc.MemberLoginProService;
import member.vo.LoginException;
import member.vo.MemberBean;

public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String memberID = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		
		MemberLoginProService mlps = new MemberLoginProService();
		
		try {
			boolean isMember = mlps.isLoginMember(memberID, password);
			
			if(isMember) {
				GetMemberService gms = new GetMemberService();
				MemberBean getMember = gms.getMember(memberID);
				
				// 이메일 인증을 완료하지 않은 경우
				if(getMember.getEmail_certify() == 0) {
					// 세션은 꼭 넘겨줘야함
					HttpSession session = request.getSession();
					session.setAttribute("registerID", memberID);
					
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script type='text/javascript'>");
					out.print("var checked = confirm('" + getMember.getEmail() + " 이메일로 인증 확인 이메일을 전송해드렸습니다.\\n이메일을 확인하세요.\\n\\n만약 이메일을 발신받지 못했을 경우 [확인]을 누르시면\\n재발신 요청이 가능합니다.');");
					out.print("if(checked) { location.href = '../member/registerEmailSend.me'; }");
					out.print("else { history.back(); } ");
					out.print("</script>");
					out.close();
					
					return forward;
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("memberID", memberID);
				
				forward = new ActionForward();
				forward.setPath("../main/index");
				forward.setRedirect(true);
			}
		} catch (LoginException e) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + e.getMessage() + "')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
