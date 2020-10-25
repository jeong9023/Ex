package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import shop.svc.ShopPointService;

public class ShopPointAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		
		if(mb_id==null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("window.close()");
			out.println("</script>");
			out.close();
			
			return forward;
		}

		ShopPointService shopPointService = new ShopPointService();
		int point = shopPointService.getPoint(mb_id);
		
		if(point > 0) {
			out.println(point+" P");
			out.close();
		} else {
			out.println("point가 없어요 ( •́ ̯•̀ )");
			out.close();
		}
		
		
		return forward;
	}

}
