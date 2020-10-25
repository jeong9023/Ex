package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import qna.svc.qnaModifyProService;
import qna.vo.qnaBean;

public class qnaReModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ModifyProAction");
		
		ActionForward forward = null;

		// 게시물 수정에 필요한 파라미터 가져오기
		int wr_id = Integer.parseInt(request.getParameter("wr_id"));
		
		String page = request.getParameter("page");

		// Bean 객체 생성 후 입력된 데이터 저장
		qnaBean article = new qnaBean();
		article.setWr_no(Integer.parseInt(request.getParameter("wr_no")));
		article.setWr_id(wr_id);
		article.setWr_co_id(Integer.parseInt(request.getParameter("wr_co_id")));
		article.setMb_id(request.getParameter("mb_id"));
		article.setWr_subject(request.getParameter("wr_subject"));
		article.setWr_content(request.getParameter("wr_content"));

		
		// 인스턴스 생성 
		qnaModifyProService qnaModifyProService = new qnaModifyProService();
		boolean isModifySuccess = qnaModifyProService.modifyArticle(article);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("qnaDetail.qa?wr_id=" + wr_id + "&page=" + page);
		}
		return forward;
	}

}
