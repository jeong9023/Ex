package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Transport;
import javax.mail.Message;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

import member.util.SHA256;
import member.vo.MemberBean;
import member.svc.GetMemberService;
import member.util.Gmail;

import common.Action;
import common.ActionForward;

public class RegisterEmailSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String registerID = (String)session.getAttribute("registerID");
		
		if(registerID == null) {
			out.println("<script>");
			out.println("alert('아이디 정보를 받아오지 못했습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		GetMemberService ress = new GetMemberService();
		MemberBean getMember = ress.getMember(registerID);

		if(getMember.getEmail_certify() == 1) {
			out.println("<script>");
			out.println("alert('이미 이메일 인증이 완료된 회원입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
//		InetAddress local = InetAddress.getLocalHost();
//		String ip = local.getHostAddress(); // 192.168.X.X
		// 접속 아이피가 다르면 세션 못받아오는 문제 있어서 하드코딩함

		// 인증받는 이용자에게 보내지는 메일
		new SHA256();
		String host = "http://192.168.1.18:8080/DDANG/member/";
		String from = "kals.mail.smtp";
		String to = getMember.getEmail();
		String subject = "[땅땅땅] 회원가입 인증 확인입니다.";
		String content = "<div style='width:400px;margin:0 auto;padding:30px;text-align:center;font-family:맑은 고딕;background:#f9f9f9;border:1px solid #e8e8e8;box-shadow:0px 0px 4px 1px #e4e4e4'>\r\n" + 
						 "<div style='margin-bottom:30px;font-size:24px;font-weight:bold;color:#fc5230;letter-spacing:-2px'>땅땅땅</div>\r\n" + 
						 "<div style='font-size:14px;text-align:left;margin-bottom:30px'>땅땅땅에 회원으로 가입하신 것을 진심으로 환영합니다!<br><br>4월25일 부터 7월10일 까지 진행한 개인간의 중고 경매 거래 개념의 주제로 구현한 4조 팀 프로젝트 사이트입니다.<br><br>기본적인 이용법은 로그인 전 메뉴 우측 상단 '땅땅땅 이용방법' 버튼을 클릭하여 간단히 읽어보시기 바랍니다.<br><br>아래 인증하기 버튼을 클릭하시면 회원가입 인증이 완료됩니다.</div>\r\n" + 
						 "<a href='" + host + "registerEmailConfirm.me?code=" + SHA256.getSHA256(to) + "' style='width:100%;display:block;color:#FFF;letter-spacing:-1px;padding:10px 0;background:#fc5230;border:1px solid #ec4524;text-decoration:none'>인증 확인</a>\r\n" + 
						 "</div>";

		// SMTP 접속 정보
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try{
		    Authenticator auth = new Gmail();
		    
		    Session ses = Session.getInstance(p, auth);
		    ses.setDebug(true);
		    
		    MimeMessage msg = new MimeMessage(ses); 
		    msg.setSubject(subject);
		    
		    Address fromAddr = new InternetAddress(from);
		    msg.setFrom(fromAddr);
		    
		    Address toAddr = new InternetAddress(to);
		    msg.addRecipient(Message.RecipientType.TO, toAddr);
		    msg.setContent(content, "text/html;charset=UTF-8");
		    
		    Transport.send(msg);
		} catch(Exception e) {
			out.print("<script type='text/javascript'>");
			out.print("alert('이메일 전송에 실패했습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
		    return forward;
		}
		
		session.setAttribute("registerID", registerID);
		session.setAttribute("alertEmail", getMember.getEmail());

		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("../main/index");
		
		return forward;
	}

}
