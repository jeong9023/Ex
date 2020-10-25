package talk.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import talk.svc.TalkMessageSendTryService;
import talk.vo.TalkBean;

public class TalkMessageSendProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		if(memberID == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		int itemNum = 0;
		String strangerID = null;
		String content = null;
		
		if(request.getParameter("item_num") != null) itemNum = Integer.parseInt(request.getParameter("item_num"));
		if(request.getParameter("stranger_id") != null) strangerID = request.getParameter("stranger_id");
		if(request.getParameter("message") != null) content = request.getParameter("message");
		
		if(itemNum == 0 || strangerID == null || content == null || strangerID == "" || content == "") {
			out.println("<script>");
			out.println("alert('메시지를 보낼 정보를 얻지 못했습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}

		TalkBean talkBean = new TalkBean();
		talkBean.setNo(0);
		talkBean.setItemNumber(itemNum);
		talkBean.setRecvMemberID(strangerID);
		talkBean.setSendMemberID(memberID);
		talkBean.setSendDatetime(new Timestamp(System.currentTimeMillis()));
		talkBean.setContent(content);
		
		TalkMessageSendTryService tmsts = new TalkMessageSendTryService();
		tmsts.messageSendTry(talkBean);
		
		forward = new ActionForward();
		forward.setRedirect(true);
		
		if(request.getParameter("recv_mb_id") != null) {
			forward.setPath("../talk/list.ta?item_num=" + itemNum + "&recv_mb_id=" + request.getParameter("recv_mb_id") + "&type=" + request.getParameter("type"));
		}
		else if(request.getParameter("send_mb_id") != null) {
			forward.setPath("../talk/list.ta?item_num=" + itemNum + "&send_mb_id=" + request.getParameter("send_mb_id") + "&type=" + request.getParameter("type"));
		}
		else {
			forward.setPath("../talk/list.ta?item_num=" + itemNum + "&type=" + request.getParameter("type"));
		}
		
		return forward;
	}

}
