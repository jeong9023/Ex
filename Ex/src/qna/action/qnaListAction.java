package qna.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import qna.svc.qnaListService;
import qna.vo.qnaBean;



public class qnaListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListAction");
		
		ActionForward forward = null;
		
		//로그인이 되어 있지 않다면 이동
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		
		System.out.println(mb_id);
		
		if(mb_id == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요한 기능입니다.');");
			out.println("location.href='../main/index'");
			out.println("</script>");
			out.close();
			return forward;
		}
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 한 페이지 당 출력할 게시물 수 지정
		
		//페이지 번호
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//게시물 전체 갯수 조회
		qnaListService qnaListService = new qnaListService();
		int listCount = qnaListService.getListCount(mb_id);
		System.out.println("전체 게시물 수 : " + listCount);
		System.out.println("현재 게시물 페이지 : " + page);
		
		//게시물 목록 데이터 받아옴.
		// => 파라미터 : 현재페이지번호(page), 한 번에 가져올 게시물 최대 갯수(limit)
		// => 리턴타입 : ArrayList<BoardBean>
		ArrayList<qnaBean> articleList = qnaListService.getArticleList(page, limit, mb_id);
		
		
		// 페이징 처리
		// 1. 최대 페이지 번호 계산
		int maxPage = (int)((double)listCount / limit + 0.95);
		// 2. 시작 페이지 번호 계산
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 3.  페이지 번호 계산
		int endPage = startPage + 10 - 1;
		 //끝 > 최대 번호일 경우 
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징 정보를 저장할 PageInfo 객체 생성 및 데이터 저장
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		
		// request 객체에 PageInfo 객체와 ArrayList 객체 저장
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		// ActionForward 객체를 생성하여 Dispatcher 방식으로 
		// 포워딩 경로를 board 폴더 내의 qna_board_list.jsp 페이지로 지정		
		forward = new ActionForward();
		forward.setPath("/qna/qna_list.jsp");
		
		return forward;
	}

}















