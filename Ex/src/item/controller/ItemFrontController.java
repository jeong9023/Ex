package item.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import item.action.ItemCategorySearchAction;
import item.action.ItemAddPriceAction;
import item.action.ItemListAction;
import item.action.ItemModifyAction;
import item.action.ItemModifyProAction;
import item.action.ItemMyitemAction;
import item.action.ItemSearchAction;
import item.action.ItemSuccessfulAction;
import item.action.ItemWriteProAction;
import item.action.ItemViewAction;
import item.action.ItemWriteAction;

@WebServlet("*.it")
public class ItemFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/item/write.it")) {
			action = new ItemWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/writePro.it")) {
			action = new ItemWriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/modify.it")) {
			action = new ItemModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/modifyPro.it")) {
			action = new ItemModifyProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/list.it")) {
			action = new ItemListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/view.it")) {
			action = new ItemViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/itemSuccessfulAction.it")) {
			action = new ItemSuccessfulAction();
			
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/itemAddPriceAcion.it")) {
			action = new ItemAddPriceAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/itemSearchAction.it")) {
			action = new ItemSearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/itemcategorySearchAction.it")) {
			action = new ItemCategorySearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/item/itemMyitemAction.it")) {
			action = new ItemMyitemAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) { // Redirect 방식 (주소 변경 O, request 객체 공유 X)
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식 (주소 변경 X, request 객체 공유 O)
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
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
