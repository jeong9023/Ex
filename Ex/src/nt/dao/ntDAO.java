package nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import nt.vo.ntBean;

import static common.JdbcUtil.*;

public class ntDAO {
	/*
	 * 싱글톤 디자인 패턴을 이용한 BoardDAO 작업
	 * 1. private 접근제한자를 사용한 생성자 정의(qb)
	 * 2. 인스턴스를 생성하여 변수에 저장
	 * 3. 외부에서 인스턴스를 전달받을 수 있도록 Getter 메서드
	 * 4. 멤버변수 static으로 정의
	 */
	private ntDAO() {
	}

	private static ntDAO instance;

	public static ntDAO getInstance() {
		// DAO 객체가 없을 경우 생성
		if (instance == null) {
			instance = new ntDAO();
		}

		return instance;
	}
	// ---------------------------------------------------------------------------------

	Connection con; // Connection 객체 전달받아 저장할 변수 선언

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 게시물 등록
	//Bean 객체를 전달받아 insert 작업 수행 후 Service로 리턴
	public int insertArticle(ntBean qb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 게시글이 존재할 경우 번호 최댓값(wr_no)를 조회하여 새 글 번호 결정(+1)
			int wr_no = 1;
			String sql = "SELECT MAX(wr_no) FROM support_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // +1 새 글번호 저장
				wr_no = rs.getInt(1) + 1;
			}

			// 게시글이 존재할 경우 번호 최댓값(wr_co_id)를 조회하여 새 글 번호 결정(+1)
			int id = 1;
			sql = "SELECT MAX(wr_co_id) FROM support_board where wr_id=0";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // +1 새 글번호 저장
				id = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO support_board VALUES (?,0,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_no); // 계산된 새 글 번호 사용
			pstmt.setInt(2, id);
			pstmt.setString(3, qb.getWr_subject());
			pstmt.setString(4, qb.getWr_content());
			pstmt.setString(5, qb.getMb_id());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ntDAO - insertArticle() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			// => 주의! Connection 객체는 Service 클래스에서 별도로 사용해야하므로 닫으면 안됨!
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
			// => static import 기능을 사용하여 db.JdbcUtil 클래스 내의 모든 static 멤버 import
			close(rs);
			close(pstmt);
		}

		return insertCount;
	}

	// 전체 게시물 수 계산
	// 작성자의 아이디를 통해 선별되어 글에 접근 가능
	public int selectListCount(String mb_id) {
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				String sql = "SELECT COUNT(wr_no) FROM support_board WHERE wr_id=0";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ntDAO - selectListCount() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	
	// 지정된 갯수 만큼의 게시물 목록 조회
	// 파라미터로 현재 페이지번호(page) 와 가져올 게시물 수(limit) 전달받음
	public ArrayList<ntBean> selectArticleList(int page, int limit, String mb_id) {
		System.out.println(page);
		System.out.println(limit);
		System.out.println(mb_id);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		// 전체 게시물을 저장할 ArrayList 객체 생성 => 제네릭 타입 ntBean 타입 지정
		ArrayList<ntBean> articleList = new ArrayList<ntBean>();

		try {
				String sql = "SELECT * FROM support_board WHERE wr_id=0 ORDER BY wr_datetime desc LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				ntBean article = new ntBean();
				article.setWr_no(rs.getInt("wr_no"));
				article.setMb_id(rs.getString("mb_id"));
				article.setWr_datetime(rs.getTimestamp("wr_datetime"));
				article.setWr_subject(rs.getString("wr_subject"));
				article.setWr_content(rs.getString("wr_content"));
				article.setWr_id(rs.getInt("wr_id"));
				article.setWr_co_id(rs.getInt("wr_co_id"));

				// 전체 레코드 저장하는 ArrayList에 article 생성
				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("ntDAO - selectArticleList() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	
	
	// 게시물 상세 정보 조회
	public ntBean selectArticle(int wr_co_id) {
		System.out.println("qdao-게시물 정보");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ntBean article = null;

		try {
			String sql = "SELECT * FROM support_board WHERE wr_co_id=? and wr_id=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_co_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new ntBean();
				article.setWr_no(rs.getInt("wr_no"));
				article.setMb_id(rs.getString("mb_id"));
				article.setWr_datetime(rs.getTimestamp("wr_datetime"));
				article.setWr_subject(rs.getString("wr_subject"));
				article.setWr_content(rs.getString("wr_content"));
				article.setWr_id(rs.getInt("wr_id"));
				article.setWr_co_id(rs.getInt("wr_co_id"));
			}

		} catch (SQLException e) {
			System.out.println("ntDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}

	

	// 글 수정 작업
	// 기존의 글을 받아와 수정
	public int updateArticle(ntBean article) {
		int updateCount = 0;

		PreparedStatement pstmt = null;
		System.out.println("qdao-updateArticle");
		try {
			// 게시글 수정
			String sql1 = "UPDATE support_board SET wr_subject=?,wr_content=? WHERE wr_id=0 and wr_co_id=?; ";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, article.getWr_subject());
			pstmt.setString(2, article.getWr_content());
			pstmt.setInt(3, article.getWr_co_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		System.out.println("updateCount: " + updateCount);
		return updateCount; //sql1의 구문 작업이 성공적으로 끝나면 sql2의 작업이 실패해도 서비스로 이동됨.
	}

	// 글 삭제 작업(글이 삭제되면 댓글도 함께 삭제)
	public int deleteArticle(int wr_co_id) {
		int deleteCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM support_board WHERE wr_id=0 and wr_co_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_co_id);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

}
