package common.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import common.svc.ChartService;
import common.svc.PPLService;
import common.svc.RandomCategoryService;
import common.svc.ShopRankService;
import common.vo.RandomCategoryBean;
import common.vo.ShopRankBean;
import item.svc.ItemListService;
import item.vo.ItemBean;

public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 베스트 상점
		ShopRankService shopRankService = new ShopRankService();
		ArrayList<ShopRankBean> shopRank = shopRankService.getRank();
		request.setAttribute("shopRank", shopRank);
		
		// 랜덤 카테고리
		RandomCategoryService randomCategoryService = new RandomCategoryService();
		ArrayList<RandomCategoryBean> randomCategory = randomCategoryService.getRandomCategory();
		request.setAttribute("randomCategory", randomCategory);
		
		// 그래프
		ChartService chartService = new ChartService();
		Map<String, Map<String, Integer>> chartData = chartService.getChartdata();
		
		// 키워드 파이 차트
		Map<String, Integer> keywordMap = chartData.get("keyword");
		Set<String> keywordSet = keywordMap.keySet();

		StringBuffer sb = new StringBuffer("");
		for(String keyword: keywordSet) {
			if(sb!=null) sb.append(",");
			
			sb.append("['"+keyword+"',"+keywordMap.get(keyword)+"]");
		}
		
		request.setAttribute("keyword", sb.toString());
		
		// 가입자수 컬럼 차트
		Map<String, Integer> numOfuserMap = chartData.get("numOfuser");
		Set<String> numOfuserSet = numOfuserMap.keySet();
		
		StringBuffer sb2 = new StringBuffer("");
		for(String numOfuser : numOfuserSet) {
			if(sb2!=null) sb2.append(",");
			
			sb2.append("['"+numOfuser+"',"+numOfuserMap.get(numOfuser)+"]");
		}
		
		request.setAttribute("numOfuser", sb2.toString());
		
		// 가격대별 컬럼 차트
		Map<String, Integer> priceMap = chartData.get("priceData");
		Set<String> priceLabel = priceMap.keySet();
		StringBuffer sb3 = new StringBuffer("");
		
		for(String priceLb : priceLabel) {
			if(sb3!=null) sb3.append(",");
			
			sb3.append("['"+priceLb+"',"+priceMap.get(priceLb)+"]");
		}
		
		request.setAttribute("priceVar", sb3.toString());
		
		// 땅땅티켓 사용한 광고 상품
		int page = 1;
		int limit = 6;
		
		ArrayList<ItemBean> articleList = PPLService.getArticleList(page, limit);
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("../main/index.jsp");
		
		return forward;
	}

}
