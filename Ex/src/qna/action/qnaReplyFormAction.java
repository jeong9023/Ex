package qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import qna.svc.qnaDetailService;
import qna.vo.qnaBean;



public class qnaReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReFormAction");
		
		ActionForward forward = null;
		
		// 게시물 수정에 필요한 파라미터 가져오기
		int wr_id = Integer.parseInt(request.getParameter("wr_id"));
		
		// 인스턴스 생성 
		qnaDetailService qnaDetailService = new qnaDetailService();
		
		qnaBean article = qnaDetailService.getArticle(wr_id);
		qnaBean article_re = qnaDetailService.getArticle_re(wr_id);
		
		request.setAttribute("article", article);
		request.setAttribute("article_re", article_re);
		

		forward = new ActionForward();
		forward.setPath("/qna/qna_reply.jsp");
		
		return forward;
	}

}

















