package market.action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import market.svc.MarketDetailService;
import market.vo.MarketBean;
import market.vo.MarketBeanForCount;

public class MarketDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession(); 
		String mb_id = (String) session.getAttribute("memberID");
		
		
		MarketDetailService service = new MarketDetailService();
		int isOpened = service.isOpened(mb_id);
		MarketBean marketBean = service.getMarketProfile(mb_id);
		MarketBeanForCount counts = service.getCounts(mb_id);
		
		
		int cookiesCount = 0;
		Cookie[] cookie = request.getCookies();
		ArrayList<Integer> it_no = new ArrayList<Integer>();
		
		if(cookie!=null) {
			int cnt = 0;
			for(Cookie c : cookie) {
				if(c.getName().indexOf("it_no"+c.getValue())>-1) {
					it_no.add(Integer.parseInt(c.getValue()));
					cookiesCount++;
				}
			}
		}
		request.setAttribute("cookiesCount", cookiesCount);
		request.setAttribute("isOpened", isOpened);
		request.setAttribute("counts", counts);
		request.setAttribute("profile", marketBean);
		
		forward = new ActionForward();
		forward.setPath("/mypage/mypage.jsp");
				
		return forward;
	}

}
