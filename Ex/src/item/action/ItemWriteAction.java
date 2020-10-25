package item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.svc.ItemWriteService;
import member.vo.MemberBean;

public class ItemWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		if(memberID == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		// 여러 정보를 조회하기 위한 서비스
		ItemWriteService iws = new ItemWriteService();
		
		int marketIsOpened = iws.marketIsOpened(memberID);
		if(marketIsOpened != 1) { // 상점 오픈 상태가 아니라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('상점 오픈을 하지 않으면 상품을 등록하실 수 없습니다.\\n\\n나의 경매장으로 이동하겠습니다!\\n상점 오픈을 해주세요.');");
			out.println("location.href = '../mypage/MarketDetail.mk';");
			out.println("</script>");
			out.close();
			
			return forward;
		}

		boolean isBan = iws.isBan(memberID);		
		if(isBan) { // 활동 제한 상태라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('사이트 규정에 어긋나는 행위로 인해 활동 제한을 받아 상품을 등록하실 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		MemberBean ticketCount = iws.selectTicket(memberID); // 땅땅티켓 보유 개수
		request.setAttribute("ticketCount", ticketCount);
		
		forward = new ActionForward();
		forward.setPath("../item/write.jsp");
		
		return forward;
	}

}
