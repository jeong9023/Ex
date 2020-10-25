package nt.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import nt.svc.ntDeleteProService;
import nt.vo.ntBean;



public class ntDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteProAction");
		
		ActionForward forward = null;
		
		// 전달받은 게시물 번호와 패스워드를 사용하여 적합한 사용자인지 판별
		int wr_co_id = Integer.parseInt(request.getParameter("wr_co_id"));
		// BoardDeleteProService 클래스의 인스턴스 생성
		ntDeleteProService qnaDeleteProService = new ntDeleteProService();

		boolean isDeleteSuccess = qnaDeleteProService.removeArticle(wr_co_id);
			
			// 만약, isDeleteSuccess 가 false 일 경우
			// 자바스크립트를 사용하여 "삭제 실패!" 출력 후 이전페이지로 돌아가기
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {

				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글이 성공적으로 삭제되었습니다.')");
				out.println("window.opener.location.href='../notice/ntList.nt'");
				out.println("window.close();");
				out.println("</script>");
			}
			
		
		return forward;
	}

}


















