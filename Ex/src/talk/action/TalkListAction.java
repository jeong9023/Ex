package talk.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import talk.svc.TalkSelectDetailService;
import talk.svc.TalkSelectRoomService;
import talk.svc.TalkSelectMemberService;
import talk.vo.TalkBean;

public class TalkListAction implements Action {
	/*
	 * 톡은 실시간 경매중인 출품된 상품을 기준으로 주고받고 할 수 있습니다.
	 * 따라서 본인은 로그인이 된 세션(memberID)으로 판단하고 출품자는 상품 번호(item_num) 파라미터로 판단합니다.
	 */
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

		int itemNum = 0;
		if(request.getParameter("item_num") != null) {
			itemNum = Integer.parseInt(request.getParameter("item_num"));
		}
		
		// 출품자 회원 아이디 가져오기
		TalkSelectMemberService sps = new TalkSelectMemberService();
		String strangerID = sps.getMemberID(itemNum); // 출품자 회원 아이디
		request.setAttribute("strangerID", strangerID);
		
		// 받은 사람(현재 로그인된 본인)을 기준으로 받은 톡방 리스트 가져오기
		TalkSelectRoomService trss = new TalkSelectRoomService();
		ArrayList<TalkBean> selectRecvRoom = trss.roomRecvSelect(memberID);
		request.setAttribute("selectRecvRoom", selectRecvRoom);
		
		// 받은 사람(현재 로그인된 본인)을 기준으로 받은 보낸 리스트 가져오기
		ArrayList<TalkBean> selectSendRoom = trss.roomSendSelect(memberID);
		request.setAttribute("selectSendRoom", selectSendRoom);
		
		// =================================================================================
		
		TalkSelectDetailService tdss = new TalkSelectDetailService();
		
		String sendMemberID = null;
		if(request.getParameter("send_mb_id") != null) {
			sendMemberID = request.getParameter("send_mb_id");
			
			// 현재 대화중인 받은 톡 내용 가져오기
			ArrayList<TalkBean> selectRecvContent = tdss.selectRecvContent(itemNum, memberID, sendMemberID);
			request.setAttribute("selectRecvContent", selectRecvContent);
		}
		
		String recvMemberID = null;
		if(request.getParameter("recv_mb_id") != null) {
			recvMemberID = request.getParameter("recv_mb_id");
			
			// 현재 대화중인 받은 톡 내용 가져오기
			ArrayList<TalkBean> selectSendContent = tdss.selectSendContent(itemNum, memberID, recvMemberID);
			request.setAttribute("selectSendContent", selectSendContent);
		}
		
		// 현재 대화중인 상품의 상품명과 썸네일 가져오기
		TalkBean selectNowItemDetail = tdss.selectNowItemDetail(itemNum);
		request.setAttribute("selectNowItemDetail", selectNowItemDetail);
		
		// 현재 대화중인 출품자의 마켓 프로필사진, 상점소개, 닉네임 가져오기
		TalkBean selectMarketProfile = tdss.selectMarketProfile(strangerID);
		request.setAttribute("selectMarketProfile", selectMarketProfile);
		
		// 현재 대화중인 출품자의 출품횟수, 낙찰횟수 가져오기
		TalkBean selectItemExp = tdss.selectItemExp(strangerID);
		request.setAttribute("selectItemExp", selectItemExp);
		
		// 현재 대화중인 출품자의 출품중인 상품 최대 4개 가져오기
		ArrayList<TalkBean> selectItemExhibiting = tdss.selectItemExhibiting(strangerID);
		request.setAttribute("selectItemExhibiting", selectItemExhibiting);
		
		// 리스트에 보여질 보낸 톡 또는 받은 톡
		if(request.getParameter("type") != null) {
			switch(request.getParameter("type")) {
				case "send":
					request.setAttribute("type", "send");
					break;
				case "recv":
					request.setAttribute("type", "recv");
					break;
			}
		}
		
		// item_num 파라미터가 존재할 경우 해당 채팅방으로 띄우기
		forward = new ActionForward();
		forward.setPath("../talk/list.jsp" + (itemNum > 0 ? "?item_num=" + itemNum : ""));
		
		return forward;
	}

}
