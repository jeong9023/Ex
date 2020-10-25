package order.svc;

import static common.JdbcUtil.*;
import static common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.orderDAO;
import order.vo.orderBean;
public class orderListService {
	public ArrayList<orderBean> getArticleList(String id) {
//		System.out.println("BoardListService - getArticleList()");
		
		ArrayList<orderBean> articleList = null;
		
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		orderDAO OrderDAO = orderDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		OrderDAO.setConnection(con);
		
		// 4. BoardDAO 클래스의 selectArticleList() 메서드를 호출져오기
		//    파라미터 : page, limit, 리턴타입 : ArrayList<BoardB하여 
		//    => 페이지번호(page)와 글 갯수(limit) 를 사용하여 
		//       지정된 번호부터 지정된 게시물 갯수만큼 게시물 가ean>
		
		articleList = OrderDAO.selectOrderList(id);
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		
		return articleList;
	}
}
