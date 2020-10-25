package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import member.svc.DuplicationEmailCheckProService;
import member.svc.DuplicationIdCheckProService;
import member.svc.DuplicationNickCheckProService;
import member.svc.RegisterProService;
import member.util.SHA256;
import member.vo.MemberBean;

public class RegisterProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		if(memberID != null) {
			out.println("<script>");
			out.println("alert('이미 로그인중입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
//		// 정규표현식 검증
//		String regexID = "/^[A-Za-z][A-Za-z0-9]{3,20}$/g";
//		String regexNick = "/^[A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/";
//		String regexEmail = "/^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$/";
//		String regexPhone = "/^\\d{3}-\\d{3,4}-\\d{4}$/";
//		
//		Pattern patternID = Pattern.compile(regexID);
//		Pattern patternNick = Pattern.compile(regexNick);
//		Pattern patternEmail = Pattern.compile(regexEmail);
//		Pattern patternPhone = Pattern.compile(regexPhone);
//		
//		Matcher matcherID = patternID.matcher(request.getParameter("register_id"));
//		Matcher matcherNick = patternNick.matcher(request.getParameter("register_nick"));
//		Matcher matcherEmail = patternEmail.matcher(request.getParameter("register_email"));
//		Matcher matcherPhone = patternPhone.matcher(request.getParameter("register_phone"));
//		
//		if(!matcherID.matches() || !matcherNick.matches() || !matcherEmail.matches() || !matcherPhone.matches()) {
//			out.print("<script type='text/javascript'>");
//			
//			if(!matcherID.matches()) out.print("alert('아이디는 첫 글자는 영문자로, 두번째 부터 영문자, 숫자를 조합한 4~20자 까지 사용 가능합니다.');");
//			else if(!matcherNick.matches()) out.print("alert('닉네임은 한글/영문자/숫자로만 2~10자 까지 사용 가능합니다.');");
//			else if(!matcherEmail.matches()) out.print("alert('이메일 형식이 올바르지 않습니다.');");
//			else if(!matcherPhone.matches()) out.print("alert('연락처 형식이 올바르지 않습니다.');");
//			
//			out.print("history.back();");
//			out.print("</script>");
//			out.close();
//			
//			return forward;
//		}
		
		MemberBean memberBean = new MemberBean();
		
		memberBean.setMemberID(request.getParameter("register_id"));
		memberBean.setPassword(request.getParameter("register_password"));
		memberBean.setNick(request.getParameter("register_nick"));
		memberBean.setEmail(request.getParameter("register_email"));
		memberBean.setZip(request.getParameter("register_zip"));
		memberBean.setAddress1(request.getParameter("register_address1"));
		memberBean.setAddress2(request.getParameter("register_address2"));
		memberBean.setPhone(request.getParameter("register_phone"));

		memberBean.setNo(0); // AUTO_INCREMENT
		memberBean.setPoint(0);
		memberBean.setEmail_certify(0);
		memberBean.setEmail_certify2(SHA256.getSHA256(request.getParameter("register_email"))); // 암호화 코드
		memberBean.setDatetime_reg(new Timestamp(System.currentTimeMillis()));
		memberBean.setDatetime_leave(null);
		memberBean.setDatetime_ban(null);
		
		// 중복 검사
		DuplicationIdCheckProService dicps = new DuplicationIdCheckProService();
		MemberBean checkID = dicps.isDuplicationId(request.getParameter("register_id"));
		
		DuplicationNickCheckProService dncps = new DuplicationNickCheckProService();
		MemberBean checkNick = dncps.isDuplicationNick(request.getParameter("register_nick"));
		
		DuplicationEmailCheckProService decps = new DuplicationEmailCheckProService();
		MemberBean checkEmail = decps.isDuplicationEmail(request.getParameter("register_email"));
		
		if(checkID != null || checkNick != null || checkEmail != null) {
			out.print("<script type='text/javascript'>");
			
			if(checkID != null) out.print("alert('이미 존재하는 아이디입니다.');");
			else if(checkNick != null) out.print("alert('이미 사용중인 닉네임입니다.');");
			else if(checkEmail != null) out.print("alert('이미 사용중인 이메일입니다.');");
			
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return forward;
		}
		
		RegisterProService rps = new RegisterProService();

		// DB Insert 성공 시
		if(rps.tryInsertMember(memberBean) == 1) {
			session.setAttribute("registerID", memberBean.getMemberID());
			
			forward = new ActionForward();
			forward.setPath("registerEmailSend.me");
		}
		
		return forward;
	}

}
