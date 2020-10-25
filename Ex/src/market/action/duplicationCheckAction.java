package market.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import market.svc.UpdateMemberService;
import member.svc.DuplicationNickCheckProService;
import member.vo.MemberBean;

public class duplicationCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		String registerNick = request.getParameter("register_nick");
		
		if(registerNick != null) {
			UpdateMemberService service = new UpdateMemberService();
			
			int dupCheck = service.duplicationCheck(memberID, registerNick);
			
			if(dupCheck==0) { //사용가능
				out.print("0");
			}else if(dupCheck==1) {//기존 닉네임 
				out.print("1");
			}else if(dupCheck==2) {//사용불가
				out.print("2");
			}else {//오류
				out.print("-1");
			}
			
		}
		out.close();
		
		return forward;
	}
}
