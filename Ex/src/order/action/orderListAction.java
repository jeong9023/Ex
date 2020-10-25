package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import order.svc.orderListService;
import order.vo.orderBean;

public class orderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memberID");
//		String id = "test2";
		
		orderListService ols = new orderListService();
		ArrayList<orderBean> articleList = ols.getArticleList(id);
		
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("/order/order_list.jsp");
		
		return forward;
	}

}
