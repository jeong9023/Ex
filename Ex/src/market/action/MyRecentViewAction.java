package market.action;

import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import item.vo.ItemBean;
import market.svc.MyRecentViewService;

public class MyRecentViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 20;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int CountCookie = 0;
		ArrayList<ItemBean> item = null;
		Cookie[] cookie = request.getCookies();
		ArrayList<Integer> it_no = new ArrayList<Integer>();
		int listCount = 0;
		if(cookie!=null) {
			for(Cookie c : cookie) {
				if(c.getName().indexOf("it_no"+c.getValue())>-1) {
					it_no.add(Integer.parseInt(c.getValue())); listCount++;
				}
			}
		}
			MyRecentViewService service = new MyRecentViewService();
			item = service.getRecentView(it_no);
			
			int maxPage = (int) ((double)listCount/limit + 0.95); 
			int startPage = (((int)((double)page/10 + 0.9))-1)*10 + 1; 
			int endPage = startPage + 10 - 1; 
			if(endPage>maxPage) {
				endPage = maxPage;
			}
		
		
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("CountCookie", CountCookie);
		request.setAttribute("item", item);
		
		forward = new ActionForward();
		forward.setPath("/mypage/myRecentView.jsp");
		
		return forward;
	}
}
