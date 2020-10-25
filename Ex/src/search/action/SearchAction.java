package search.action;

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

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
	
		String keyWord = request.getParameter("keyword");
		
		
		SearchService ssv = new SearchService();
		ArrayList<ItemBean> articleList = ssv.getArticleList(keyWord);
		ssv.saveKeyword(keyWord);
//		ArrayList<SearchBean> popularKeyword = ssv.getPopularKeyword();
		request.setAttribute("articleList", articleList);
//		request.setAttribute("popularKeyword", popularKeyword);
		forward = new ActionForward();
		forward.setPath("/main/searchPro.jsp?keyWord");
		

		String sKeyword = URLEncoder.encode(request.getParameter("keyword"), "UTF-8");

		Cookie[] cookies = request.getCookies();


		Cookie keyword = null;

			



		int start = 0;
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("keyword1")){
				start=1;
				}else{
				keyword = new Cookie("keyword1", sKeyword);
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
				}
		}
		

		if(start==1){
		 for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("keyword1")){
				keyword = new Cookie("keyword2", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			
				 keyword = new Cookie("keyword1", sKeyword);
				 keyword.setMaxAge(60*60*24*365);
				 response.addCookie(keyword);
				 
			}if(cookies[i].getName().equals("keyword2")){
				keyword = new Cookie("keyword3", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword3")){
				keyword = new Cookie("keyword4", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword4")){
				keyword = new Cookie("keyword5", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword5")){
				keyword = new Cookie("keyword6", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword6")){
				keyword = new Cookie("keyword7", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword7")){
				keyword = new Cookie("keyword8", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword8")){
				keyword = new Cookie("keyword9", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}if(cookies[i].getName().equals("keyword9")){
				keyword = new Cookie("keyword10", cookies[i].getValue());
				keyword.setMaxAge(60*60*24*365);
				response.addCookie(keyword);
			}
		 }
		}

		
		
		return forward;
	}

}
