package nt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import nt.svc.ntDetailService;
import nt.vo.ntBean;


public class ntModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ModifyFormAction");
		
		ActionForward forward = null;
		
		// 게시물 수정에 필요한 파라미터 가져오기
		int wr_co_id = Integer.parseInt(request.getParameter("wr_co_id"));
		
		// 인스턴스 생성  
		ntDetailService qnaDetailService = new ntDetailService();
		
		ntBean article = qnaDetailService.getArticle(wr_co_id);
		request.setAttribute("article", article);
		
		forward = new ActionForward();
		forward.setPath("/notice/nt_modify.jsp");
		
		return forward;
	}

}













