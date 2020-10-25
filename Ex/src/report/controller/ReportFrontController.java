package report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import report.action.ReportAdminAction;
import report.action.ReportAjaxAction;
import report.action.ReportAjaxAllAction;
import report.action.ReportAjaxDefAction;
import report.action.ReportAjaxRiskAction;
import report.action.ReportDeleteAction;
import report.action.ReportListAction;
import report.action.ReportRestrictAction;
import report.action.ReportRestrictClearAction;
import report.action.ReportSearchAction;
import report.action.ReportStatsProAction;
import report.action.ReportSupAction;
import report.action.ReportViewAction;
import report.action.ReportWriteIProAction;
import report.action.ReportWriteUProAction;
@WebServlet("*.re")
public class ReportFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		Action action = null;
		ActionForward forward = null;
		if(command.equals("/report/main.re")) {
			forward = new ActionForward();
			forward.setPath("/report/main.jsp");
		}else if(command.equals("/report/inquiry.re")) {
			forward = new ActionForward();
			forward.setPath("/report/inquiry.jsp");
		}else if(command.equals("/report/user.re")) {
			forward = new ActionForward();
			forward.setPath("/report/user.jsp");
		}else if(command.equals("/report/reportWriteUPro.re")) {
			action = new ReportWriteUProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/report/product.re")) {
			forward = new ActionForward();
			forward.setPath("/report/product.jsp");
		}else if(command.equals("/report/reportWriteIPro.re")) {
			action = new ReportWriteIProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/report/list.re")) {
			action = new ReportListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/admin.re")) {
			action = new ReportAdminAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		} else if(command.equals("/report/inquiry_ajax.re")) {
			action = new ReportAjaxAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/inquiry_ajax_all.re")) {
			action = new ReportAjaxAllAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/inquiry_ajax_def.re")) {
			action = new ReportAjaxDefAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/inquiry_ajax_risk.re")) {
			action = new ReportAjaxRiskAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/view.re")) {
			action = new ReportViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/reportStatsPro.re")) {
			action = new ReportStatsProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/search.re")) {
			forward = new ActionForward();
			forward.setPath("/report/search.jsp");
		} else if(command.equals("/report/reportSearch.re")) {
			action = new ReportSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/report/reportDelete.re")) {
			action = new ReportDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/report/restrict.re")){
			action = new ReportRestrictAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/report/restrictSup.re")) {
			action = new ReportSupAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/report/restrictClear.re")) {
			action = new ReportRestrictClearAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
		
	}//
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
