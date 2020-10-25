package search.action;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import item.vo.ItemBean;
import order.svc.orderListService;
import order.vo.orderBean;
import search.svc.SearchService;
import search.vo.SearchBean;

public class PopularKeywordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
	
		SearchService ssv = new SearchService();
		ArrayList<SearchBean> popularKeyword = ssv.getPopularKeyword();
		
		request.setAttribute("popularKeyword", popularKeyword);
//		forward = new ActionForward();
//		forward.setPath("/main/search_tab.jsp");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		for(int i=0; i<popularKeyword.size(); i++) {
			out.print("<span>");
			out.print("<a href=\'searchPro.sr?keyword="+popularKeyword.get(i).getKeyWord()+"\'>");
			out.print(1+i+"."+popularKeyword.get(i).getKeyWord());
			out.print("</a>");
			out.println("</span>");
			
		}
		out.println("<ul>");
		out.println("<li></li>");
		out.println("<li class='close'><a href='#'>닫기</a></li>");
		out.println("</ul>");
		
		
		return null;
		
		
		
		
		
		
		
		
//		Cookie keyword = null;
//		Cookie[] cookies = request.getCookies();
		
		
//		keyword = new Cookie("pop1", URLEncoder.encode(popularKeyword.get(0).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop2", URLEncoder.encode(popularKeyword.get(1).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop3", URLEncoder.encode(popularKeyword.get(2).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop4", URLEncoder.encode(popularKeyword.get(3).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop5", URLEncoder.encode(popularKeyword.get(4).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop6", URLEncoder.encode(popularKeyword.get(5).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop7", URLEncoder.encode(popularKeyword.get(6).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop8", URLEncoder.encode(popularKeyword.get(7).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop9", URLEncoder.encode(popularKeyword.get(8).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
//		
//		keyword = new Cookie("pop10", URLEncoder.encode(popularKeyword.get(9).getKeyWord(),"UTF-8"));
//		keyword.setMaxAge(60*60*24*365);
//		response.addCookie(keyword);
		
		
		
		
		
		
		
	}

}
