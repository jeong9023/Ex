package common.svc;

import static common.JdbcUtil.close;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import common.dao.CommonDAO;
import item.vo.ItemBean;

public class PPLService {

	// 전체 상품 갯수 가져오기
	public int getListCount() {
		int listCount = 0;
		
		Connection con = getConnection();
		
		CommonDAO commonDAO = CommonDAO.getInstance();
		commonDAO.setConnection(con);
		
		listCount = commonDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	public static ArrayList<ItemBean> getArticleList(int page, int limit) {
		ArrayList<ItemBean> articleList = null;
		
		Connection con = getConnection();
		
		CommonDAO commonDAO = CommonDAO.getInstance();
		commonDAO.setConnection(con);
		
		// 페이지 번호(page), 글 갯수(limit) 
		articleList = commonDAO.selectArticleList(page, limit);

		close(con);

		return articleList;
	}
}
