package item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.svc.ItemAddPriceService;
import item.svc.ItemMaxPriceService;
import item.svc.ItemSuccessfulService;

public class ItemAddPriceAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// itemAddPriceService
		int it_no = Integer.parseInt(request.getParameter("it_no"));
		int price = Integer.parseInt(request.getParameter("price"));
		String memberID = request.getParameter("memberID");
		
		// itemSuccessfulService
		HttpSession session = request.getSession();
		String customerID = (String)session.getAttribute("memberID");
		request.setAttribute("customerID", customerID);
		String endTime = request.getParameter("endTime");
		// itemMaxPriceService
		int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		int nowPrice = Integer.parseInt(request.getParameter("nowPrice"));
		
		//==================================================================
		ItemAddPriceService itemAddPriceService = new ItemAddPriceService();
		int addPrice = itemAddPriceService.getAddPrice(it_no,price); // 현재가 증가
		itemAddPriceService.getAdduser(it_no,customerID); // 입찰자 증가
		//==================================================================
//		ItemSuccessfulService itemSuccessfulService = new ItemSuccessfulService();
//		itemSuccessfulService.getSuccessful(memberID, customerID, it_no, endTime);
//		System.out.println("현재 로그인된 세션 ID : " + customerID);
//		System.out.println("출품자 ID : " + memberID);
		//==================================================================
		ItemMaxPriceService itemMaxPriceService = new ItemMaxPriceService();
		itemMaxPriceService.getMaxPrice(maxPrice, customerID, it_no, nowPrice, price);
		/*
		 * 낙찰조건 1. 입찰한 뒤 상품의 최대가(낙찰가)일 때 trade > tr_flag 2번으로 낙찰
		 * 낙찰조건 2. 누군가가 뷰 페이지를 봤을 때(ViewAction) 마감일시가 현재일시를 넘어갔을 때 입찰자가 1명 이상이면 마지막 입찰자 낙찰, 아무 입찰자도 없으면 유찰
		 */
		
		request.setAttribute("addPrice", addPrice);

//		forward = new ActionForward();
//		forward.setPath("view.it?no=" + it_no);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		return forward;
	}

}
