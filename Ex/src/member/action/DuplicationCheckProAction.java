package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import member.svc.DuplicationIdCheckProService;
import member.svc.DuplicationNickCheckProService;
import member.vo.MemberBean;

public class DuplicationCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String registerID = request.getParameter("register_id");
		String registerNick = request.getParameter("register_nick");
		
		if(registerID != null) {
			DuplicationIdCheckProService dicps = new DuplicationIdCheckProService();
			
			MemberBean isExist = dicps.isDuplicationId(registerID);
			
			if(isExist == null) { // 미중복
				out.print("0");
			} else if(registerID.equals(isExist.getMemberID())) { // 중복
				out.print("1");
			}
		} else if(registerNick != null) {
			DuplicationNickCheckProService dncps = new DuplicationNickCheckProService();
			
			MemberBean isExist = dncps.isDuplicationNick(registerNick);
			
			if(isExist == null) { // 미중복
				out.print("0");
			} else if(registerNick.equals(isExist.getNick())) { // 중복
				out.print("1");
			}
		}
		
		out.close();
		
		return forward;
	}

}
