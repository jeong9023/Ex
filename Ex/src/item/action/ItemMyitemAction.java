package item.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.PageInfo;
import item.svc.ItemCategorySearchService;
import item.svc.ItemMyitemService;
import item.vo.ItemBean;

public class ItemMyitemAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		HttpSession session = request.getSession();
		String memberID = (String) session.getAttribute("memberID");
		request.setAttribute("memberID", memberID);
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 한 페이지 당 출력할 게시물 수 지정
		
		// 파라미터로 전달받은 페이지 번호가 있을 경우 가져와서 page 변수에 저장
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // String -> int 변환
		}
		
		// BoardListService 클래스 인스턴스 생성
		// 게시물 전체 갯수를 조회하기 위해 getListCount() 메서드 호출하여 갯수 리턴받기
		ItemMyitemService itemMyitemService = new ItemMyitemService();
		int listCount = itemMyitemService.getListCount();
		
		ArrayList<ItemBean> articleList = itemMyitemService.getArticleList(page, limit,memberID);

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
