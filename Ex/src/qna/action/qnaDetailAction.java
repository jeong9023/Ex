package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import qna.svc.qnaDetailService;
import qna.vo.qnaBean;



public class qnaDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailAction");
		
		ActionForward forward = null;
		
		// 파라미터로 전달된 게시물 번호 가져오기
		int wr_id = Integer.parseInt(request.getParameter("wr_id"));
		System.out.println(wr_id);
		
		// BoardDetailService 인스턴스 생성 후 getArticle() 메서드 호출하여 상세내용 가져오기
		// => 파라미터 : 게시물 번호(board_num), 리턴타입 : BoardBean(article)
		qnaDetailService qnaDetailService = new qnaDetailService();
		qnaBean article = qnaDetailService.getArticle(wr_id);
		
		qnaBean article_re = qnaDetailService.getArticle_re(wr_id);
		
		// request 객체에 BoardBean 객체 저장
		request.setAttribute("article", article);
		request.setAttribute("article_re", article_re);
		
		// board 폴더 내의 qna_board_view.jsp 페이지로 포워딩
		// => 요청된 서블릿 주소가 유지되므로 Dispatcher 방식으로 포워딩
		forward = new ActionForward();
		forward.setPath("/qna/qna_view.jsp");
		
		return forward;
	}

}






