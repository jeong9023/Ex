package faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.Action;
import common.ActionForward;
import qna.action.qnaDeleteProAction;
import qna.action.qnaDetailAction;
import qna.action.qnaListAction;
import qna.action.qnaModifyFormAction;
import qna.action.qnaModifyProAction;
import qna.action.qnaReModifyFormAction;
import qna.action.qnaReModifyProAction;
import qna.action.qnaReplyFormAction;
import qna.action.qnaReplyProAction;
import qna.action.qnaWriteProAction;
import qna.vo.qnaBean;



@WebServlet("*.fq") //XXX.qa 주소에 대한 요청을 전달받음
public class faqFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/faq/fqaList.fq")) {
			// 글쓰기 페이지 요청은 비즈니스 로직이 없는 View 페이지(JSP)로 바로 연결 수행
			forward=new ActionForward();
			forward.setPath("/faq/faqview.jsp");
			System.out.println(forward);
		} 
		
		
		
		// ActionForward 객체 내의 포워딩 방식에 따라 각각의 포워딩 작업 수행
		if(forward != null) {
			if(forward.isRedirect()) { // Redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				// => RequestDispatcher 객체의 forward() 메서드를 호출하여 request, response 객체 전달
				dispatcher.forward(request, response);
			}
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
