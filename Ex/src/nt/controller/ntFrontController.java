package nt.controller;

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
import nt.action.ntDeleteProAction;
import nt.action.ntDetailAction;
import nt.action.ntListAction;
import nt.action.ntModifyFormAction;
import nt.action.ntModifyProAction;
import nt.action.ntWriteProAction;
import nt.vo.ntBean;



@WebServlet("*.nt") //XXX.nt 주소에 대한 요청을 전달받음
public class ntFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/notice/ntWrite.nt")) {
			// 글쓰기 페이지 요청은 비즈니스 로직이 없는 View 페이지(JSP)로 바로 연결 수행
			forward = new ActionForward();
			forward.setPath("/notice/nt_write.jsp");
			System.out.println("Controller-writeform");
		} else if(command.equals("/notice/ntWritePro.nt")) {
			action = new ntWriteProAction();
			System.out.println("Controller-writepro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/notice/ntList.nt")) {
			action = new ntListAction();
			System.out.println("Controller-list");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/notice/ntDetail.nt")) {
			action = new ntDetailAction();
			System.out.println("Controller-detail");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/notice/ntModify.nt")) {
			action = new ntModifyFormAction();
			System.out.println("Controller-modifyform");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/notice/ntModifyPro.nt")) {
			action = new ntModifyProAction();
			System.out.println("Controller-modifypro");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/notice/ntDelete.nt")) {
			forward = new ActionForward();
			forward.setPath("/notice/nt_delete.jsp");
			System.out.println("Controller-ntDeleteForm");
		} else if(command.equals("/notice/ntDeletePro.nt")) {
			action = new ntDeleteProAction();
			System.out.println("Controller-deletepro");
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
