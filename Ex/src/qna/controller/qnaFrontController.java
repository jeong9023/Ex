package qna.controller;

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



@WebServlet("*.qa") //XXX.qa 주소에 대한 요청을 전달받음
public class qnaFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/qna/qnaWrite.qa")) {
			// 글쓰기 페이지 요청은 비즈니스 로직이 없는 View 페이지(JSP)로 바로 연결 수행
			forward = new ActionForward();
			forward.setPath("/qna/qna_write.jsp");
			System.out.println("Controller-writeform");
		} else if(command.equals("/qna/qnaWritePro.qa")) {
			action = new qnaWriteProAction();
			System.out.println("Controller-writepro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaList.qa")) {
			action = new qnaListAction();
			System.out.println("Controller-list");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaDetail.qa")) {
			action = new qnaDetailAction();
			System.out.println("Controller-detail");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaModify.qa")) {
			action = new qnaModifyFormAction();
			System.out.println("Controller-modifyform");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/qna/qnaModifyPro.qa")) {
			action = new qnaModifyProAction();
			System.out.println("Controller-modifypro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaDelete.qa")) {
			forward = new ActionForward();
			forward.setPath("/qna/qna_delete.jsp");
			System.out.println("Controller-detailform");
		} else if(command.equals("/qna/qnaDeletePro.qa")) {
			action = new qnaDeleteProAction();
			System.out.println("Controller-detailpro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaReply.qa")) {
			// 원본 게시물 정보 조회
			action = new qnaReplyFormAction();
			System.out.println("Controller-replyform");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qna/qnaReplyPro.qa")) {
			action = new qnaReplyProAction();
			System.out.println("Controller-replypro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
