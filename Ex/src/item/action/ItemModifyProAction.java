package item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.svc.ItemModifyService;
import item.vo.ItemBean;

public class ItemModifyProAction implements Action {

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

		ItemBean itemBean = new ItemBean();
		
		itemBean.setNo(Integer.parseInt(request.getParameter("no")));
		itemBean.setName(request.getParameter("subject"));
		itemBean.setCategory(request.getParameter("category"));
		itemBean.setStartPrice(Integer.parseInt(request.getParameter("price_start").replace(",", "")));
		itemBean.setMaxPrice(Integer.parseInt(request.getParameter("price_end").replace(",", "")));
		itemBean.setDeliveryPrice(Integer.parseInt(request.getParameter("price_delivery").replace(",", "")));
		itemBean.setEndTime(request.getParameter("datetime_end"));
		itemBean.setContent(request.getParameter("content"));
		
		ItemModifyService ims = new ItemModifyService();
		ims.tryModify(itemBean);

		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("view.it?no=" + itemBean.getNo());
		
		return forward;
	}

}
