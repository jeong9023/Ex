package qna.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import qna.svc.qnaWriteProService;
import qna.vo.qnaBean;

public class qnaWriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("qnaWriteProAction");
		
		ActionForward forward = null;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("memberID");
		
		if(mb_id == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return forward;
		}
		
		ServletContext context = request.getServletContext();
		
		// Bean 객체 생성 후 입력된 데이터 저장
		qnaBean qb = new qnaBean();
		qb.setMb_id(mb_id);
		qb.setWr_subject(request.getParameter("wr_subject"));
		qb.setWr_content(request.getParameter("wr_content"));
		
		// 인스턴스 생성 
		qnaWriteProService qnaWriteProService = new qnaWriteProService();

		boolean isWriteSuccess = qnaWriteProService.registArticle(qb);
		
		// 리턴받은 결과를 사용하여 글 등록 결과 판별
		if(!isWriteSuccess) {
			
			out.println("<script>");
			out.println("alert('문의를 등록함에 있어 오류가 발견되었습니다.')");
			out.println("asdf");
			out.println("</script>");
			out.close();
		}
		else {
			System.out.println("글 등록 성공!");
			
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('문의가 성공적으로 등록되었습니다!');");
			out.println("window.opener.location.reload();");
			out.println("window.close();");
			out.println("</script>"); // 자바스크립트 끝 태그
			out.close();
			
			forward = new ActionForward();
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
