package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import qna.svc.qnaReplyProService;
import qna.vo.qnaBean;


public class qnaReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReplyProAction");
		
		ActionForward forward = null;

		qnaBean article_re = new qnaBean();
		
		// Bean 객체 생성 후 입력된 데이터 저장
		article_re.setWr_no(Integer.parseInt(request.getParameter("wr_no")));
		article_re.setMb_id(request.getParameter("mb_id"));
		article_re.setWr_subject(request.getParameter("re_subject"));
		article_re.setWr_content(request.getParameter("re_content"));
		article_re.setWr_id(Integer.parseInt(request.getParameter("wr_id")));
		article_re.setWr_co_id(Integer.parseInt(request.getParameter("wr_co_id")));
		
		// 인스턴스 생성 
		qnaReplyProService qnaReplyProService = new qnaReplyProService();
		
		boolean isReplySuccess = qnaReplyProService.replyArticle(article_re);
		
		if(!isReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			System.out.println(request.getParameter("page"));
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("qnaDetail.qa?wr_id="+article_re.getWr_id()+"&page=" + request.getParameter("page"));
		}
		
		return forward;
	}

}

















