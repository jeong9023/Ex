package market.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import market.action.MarketDetailAction;
import market.action.MyBiddingListAction;
import market.action.MyItemListAction;
import market.action.MyRecentViewAction;
import market.action.MyTradeListAction;
import market.action.OpenMarketFormAction;
import market.action.OpenMarketProAction;
import market.action.UpdateMemberAction;
import market.action.UpdateMemberFormAction;
import market.action.duplicationCheckAction;



@WebServlet("*.mk")
public class MarketFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getServletPath();
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/mypage/OpenMarketForm.mk")) {
			action = new OpenMarketFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/OpenMarketPro.mk")) {
			action = new OpenMarketProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/MarketDetail.mk")) {
			action = new MarketDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/UpdateMarketPro.mk")) {
			action = new OpenMarketProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/MyItemList.mk")) {
			action = new MyItemListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/MyRecentView.mk")) {
			action = new MyRecentViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mypage/MyTradeList.mk")) {
			action = new MyTradeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.contentEquals("/mypage/UpdateMemberForm.mk")) {
			action = new UpdateMemberFormAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.contentEquals("/mypage/UpdateMember.mk")) {
			action = new UpdateMemberAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.contentEquals("/mypage/duplicationCheckPro.mk")) {
			action = new duplicationCheckAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.contentEquals("/mypage/MyBiddingList.mk")) {
			action = new MyBiddingListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
