package nt.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import nt.svc.ntDetailService;
import nt.vo.ntBean;



public class ntDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailAction");
		
		ActionForward forward = null;
		
		// 파라미터로 전달된 게시물 번호 가져오기
		int wr_co_id = Integer.parseInt(request.getParameter("wr_co_id"));
		System.out.println(wr_co_id);
		
		// BoardDetailService 인스턴스 생성 후 getArticle() 메서드 호출하여 상세내용 가져오기
		// => 파라미터 : 게시물 번호(board_num), 리턴타입 : BoardBean(article)
		ntDetailService qnaDetailService = new ntDetailService();
		ntBean article = qnaDetailService.getArticle(wr_co_id);
		
		// request 객체에 BoardBean 객체 저장
		request.setAttribute("article", article);
		
//		String wr_content=article.getWr_content();
//		wr_content.replace("\r\n", "<br>");
		
		// board 폴더 내의 qna_board_view.jsp 페이지로 포워딩
		// => 요청된 서블릿 주소가 유지되므로 Dispatcher 방식으로 포워딩
		forward = new ActionForward();
		forward.setPath("/notice/nt_view.jsp");
		
		return forward;
	}

}






