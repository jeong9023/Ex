package nt.svc;

import java.sql.Connection;
import java.util.ArrayList;

import nt.dao.ntDAO;
import nt.vo.ntBean;

import static common.JdbcUtil.*;

public class ntListService {
	
	// 전체 게시물 목록 갯수 가져오기
	public int getListCount(String mb_id) {
		System.out.println("ListService - getListCount()");
		int listCount = 0;
		
		Connection con = getConnection(); //DB 작업에 필요한 Connection 객체 호출
		ntDAO ntDao = ntDAO.getInstance(); //Dao객체(싱글톤 패턴 형태) 생성
		ntDao.setConnection(con); //객체 전달
		
		//전체 게시물 수 계산 selectListCount
		listCount = ntDao.selectListCount(mb_id); 
		
		close(con); //객체 반환
		
		return listCount; //결과 리턴
	}

	public ArrayList<ntBean> getArticleList(int page, int limit, String mb_id) {
		System.out.println("ListService - getArticleList()");
		System.out.println(mb_id);
		System.out.println(page);
		System.out.println(limit);
		
		ArrayList<ntBean> articleList = null;
		
		Connection con = getConnection();
		ntDAO ntDao = ntDAO.getInstance();
		ntDao.setConnection(con);
		
		// 지정된 갯수만큼 게시물 가져오기 selectArticleList
			articleList = ntDao.selectArticleList(page, limit, mb_id);
		
		close(con);
		
		
		return articleList;
	}
	
}

















