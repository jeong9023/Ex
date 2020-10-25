package item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.svc.ItemModifyService;
import item.svc.ItemWriteService;
import item.vo.ItemBean;

public class ItemModifyAction implements Action {

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
		
		boolean isBan = iws.isBan(memberID);
		if(isBan) { // 활동 제한 상태라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('사이트 규정에 어긋나는 행위로 인해 활동 제한을 받아 상품을 수정하실 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		ItemModifyService ims = new ItemModifyService();
		boolean isWriter = ims.isWriter(no, memberID);
		
		// 글쓴이가 아니라면
		if(!isWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('수정 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		boolean isDelete = ims.isDelete(no);
		if(isDelete) { // 삭제된 상품이라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('이미 삭제된 상품입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		int isPossible = ims.isPossible(no);
		if(isPossible == 2) { // 낙찰된 상품이라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('이미 낙찰된 상품은 수정할 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		} else if(isPossible == 3) { // 유찰된 상품이라면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('유찰된 상품은 수정할 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		ItemBean itemBean = ims.selectArticle(no);
		
		if(itemBean != null) {
			request.setAttribute("article", itemBean);
			
			forward = new ActionForward();
			forward.setPath("../item/modify.jsp?no=" + no);
		}
		
		return forward;
	}

}
