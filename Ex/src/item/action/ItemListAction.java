package item.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import item.svc.ItemListService;
import item.vo.ItemBean;

public class ItemListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 한 페이지 당 출력할 게시물 수 지정
		
		// 파라미터로 전달받은 페이지 번호가 있을 경우 가져와서 page 변수에 저장
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // String -> int 변환
		}
		
		// BoardListService 클래스 인스턴스 생성
		// 게시물 전체 갯수를 조회하기 위해 getListCount() 메서드 호출하여 갯수 리턴받기
		ItemListService itemListService = new ItemListService();
		int listCount = itemListService.getListCount();
		
		// 지정한 갯수 만큼의 게시물 가져오기
		// BoardListService 클래스의 getArticleList() 메서드를 호출하여 게시물 목록 가져오기
		// => 파라미터 : 현재페이지번호(page), 한 번에 가져올 게시물 최대 갯수(limit)
		// => 리턴타입 : ArrayList<BoardBean> => 게시물 1개 저장할 BoardBean 제네릭 타입으로 지정
		ArrayList<ItemBean> articleList = ItemListService.getArticleList(page, limit);

		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		
		int endPage = startPage+10-1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, endPage, startPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("list.jsp");
		
		return forward;
	}

}
