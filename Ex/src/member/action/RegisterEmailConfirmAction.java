package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;

import member.util.SHA256;
import member.vo.MemberBean;
import member.svc.GetMemberService;
import member.svc.SetMemberService;
import common.Action;
import common.ActionForward;

public class RegisterEmailConfirmAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String registerID = (String)session.getAttribute("registerID");
		
		if(registerID == null) {
			out.println("<script>");
			out.println("alert('아이디 정보를 받아오지 못했습니다.\\n\\n오랜 시간이 지난 후 인증하는 경우라면\\n로그인을 시도해보시기 바랍니다.');");
			out.println("location.href = '../main/index'");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		GetMemberService rms = new GetMemberService();
		MemberBean getMember = rms.getMember(registerID);
		
		if(getMember.getEmail_certify() == 1) {
			out.println("<script>");
			out.println("alert('이미 이메일 인증이 완료된 회원입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		String code = request.getParameter("code");
		
		new SHA256();
		boolean rightCode = (SHA256.getSHA256(getMember.getEmail()).equals(code)) ? true : false;
		
		// 코드 유효하다면 암호화 컬럼 초기화 및 인증 완료 업데이트
		if(rightCode) {
			SetMemberService sms = new SetMemberService();
			sms.setMemberEmailConfirm(registerID);
			
			session.invalidate();
			
			out.print("<script type='text/javascript'>");
			out.print("alert('이메일 인증에 성공했습니다!\\n로그인을 해주세요.');");
			out.print("location.href = '../main/index'");
			out.print("</script>");
			out.close();
			
			return forward;
		} else {
			out.print("<script type='text/javascript'>");
			out.print("alert('유효하지 않은 코드입니다.');");
			out.print("</script>");
			out.close();
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("../main/index");
		
		return forward;
	}

}
