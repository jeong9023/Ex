package market.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import item.vo.ItemBean;
import market.svc.MyItemListService;

public class MyItemListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String mb_id = (String) session.getAttribute("memberID");
		
		int page = 1;
		int limit = 20;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		MyItemListService service = new MyItemListService();
		int listCount = service.ItemlistCount(mb_id);
		
		ArrayList<ItemBean> item = service.getItemList(mb_id, page, limit);
		
		
		int maxPage = (int) ((double)listCount/limit + 0.95); 
		int startPage = (((int)((double)page/10 + 0.9))-1)*10 + 1; 
		int endPage = startPage + 10 - 1; 
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("item", item);
		
		forward = new ActionForward();
		forward.setPath("/mypage/myItemList.jsp");

		return forward;
	}
}
