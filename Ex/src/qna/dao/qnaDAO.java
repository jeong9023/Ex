package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import qna.vo.qnaBean;

import static common.JdbcUtil.*;

public class qnaDAO {
	/*
	 * 싱글톤 디자인 패턴을 이용한 BoardDAO 작업
	 * 1. private 접근제한자를 사용한 생성자 정의(qb)
	 * 2. 인스턴스를 생성하여 변수에 저장
	 * 3. 외부에서 인스턴스를 전달받을 수 있도록 Getter 메서드
	 * 4. 멤버변수 static으로 정의
	 */
	private qnaDAO() {
	}

	private static qnaDAO instance;

	public static qnaDAO getInstance() {
		// DAO 객체가 없을 경우 생성
		if (instance == null) {
			instance = new qnaDAO();
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
	public int insertArticle(qnaBean qb) {
		int insertCount = 0;
		int insertCount2 = 0;

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

			// 게시글이 존재할 경우 번호 최댓값(wr_id)를 조회하여 새 글 번호 결정(+1)
			int id = 1;
			sql = "SELECT MAX(wr_id) FROM support_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // +1 새 글번호 저장
				id = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO support_board VALUES (?,?,0,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_no); // 계산된 새 글 번호 사용
			pstmt.setInt(2, id);
			pstmt.setString(3, qb.getWr_subject());
			pstmt.setString(4, qb.getWr_content());
			pstmt.setString(5, qb.getMb_id());
			insertCount = pstmt.executeUpdate();
			
			//댓글 자동 생성
			String sql2 = "INSERT INTO support_board VALUES (?,?,1,?,?,now(),?)";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, wr_no+1); // 계산된 새 글 번호 사용
			pstmt.setInt(2, id);
			pstmt.setString(3, "re : "+qb.getWr_subject());
			pstmt.setString(4, "현재 문의주신 내용은 답변 대기중 입니다.");
			pstmt.setString(5, qb.getMb_id());
			insertCount2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("qnaDAO - insertArticle() 실패! : " + e.getMessage());
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
			if (mb_id.equals("admin")) { //관리자일 때 모든 글
				String sql = "SELECT COUNT(wr_no) FROM support_board WHERE wr_co_id=0";
				pstmt = con.prepareStatement(sql);
			} else { //회원일 때 회원의 글만
				String sql = "SELECT COUNT(wr_no) FROM support_board WHERE wr_co_id=0 and mb_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb_id);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("qnaDAO - selectListCount() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	
	// 지정된 갯수 만큼의 게시물 목록 조회
	// 파라미터로 현재 페이지번호(page) 와 가져올 게시물 수(limit) 전달받음
	public ArrayList<qnaBean> selectArticleList(int page, int limit, String mb_id) {
		System.out.println(page);
		System.out.println(limit);
		System.out.println(mb_id);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산

		// 전체 게시물을 저장할 ArrayList 객체 생성 => 제네릭 타입 qnaBean 타입 지정
		ArrayList<qnaBean> articleList = new ArrayList<qnaBean>();

		try {
			if (mb_id.equals("admin")) { //관리자일 때 모든 글 조회
				String sql = "SELECT * FROM support_board " + "WHERE wr_co_id=0 " + "ORDER BY wr_id DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);

			} else { // 회원일 때 자신의 글만 조회
				String sql = "SELECT * FROM support_board " + "WHERE wr_co_id=0 and mb_id=? "
						+ "ORDER BY wr_id DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb_id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
			}
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				qnaBean article = new qnaBean();
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
			System.out.println("qnaDAO - selectArticleList() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	
	
	// 게시물 상세 정보 조회
	public qnaBean selectArticle(int wr_id) {
		System.out.println("qdao-게시물 정보");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		qnaBean article = null;

		try {
			String sql = "SELECT * FROM support_board WHERE wr_id=? and wr_co_id=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new qnaBean();
				article.setWr_no(rs.getInt("wr_no"));
				article.setMb_id(rs.getString("mb_id"));
				article.setWr_datetime(rs.getTimestamp("wr_datetime"));
				article.setWr_subject(rs.getString("wr_subject"));
				article.setWr_content(rs.getString("wr_content"));
				article.setWr_id(rs.getInt("wr_id"));
				article.setWr_co_id(rs.getInt("wr_co_id"));
			}

		} catch (SQLException e) {
			System.out.println("qnaDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}

	
	
	// 댓글 상세 정보 가져오기
	public qnaBean selectArticle_re(int wr_id) {
		System.out.println("qdao-게시물 정보");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		qnaBean article_re = null;

		try {
			String sql = "SELECT * FROM support_board WHERE wr_id=? and wr_co_id=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article_re = new qnaBean();
				article_re.setWr_no(rs.getInt("wr_no"));
				article_re.setMb_id(rs.getString("mb_id"));
				article_re.setWr_datetime(rs.getTimestamp("wr_datetime"));
				article_re.setWr_subject(rs.getString("wr_subject"));
				article_re.setWr_content(rs.getString("wr_content"));
				article_re.setWr_id(rs.getInt("wr_id"));
				article_re.setWr_co_id(rs.getInt("wr_co_id"));
			}
		} catch (SQLException e) {
			System.out.println("qnaDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return article_re;
	}

	// 글 수정 작업
	// 기존의 글을 받아와 수정
	public int updateArticle(qnaBean article) {
		int updateCount = 0;
		int updateCount2 = 0;

		PreparedStatement pstmt = null;
		System.out.println("qdao-updateArticle");
		try {
			// 게시글 수정
			String sql1 = "UPDATE support_board SET wr_subject=?,wr_content=? WHERE wr_id=? and wr_co_id=0; ";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, article.getWr_subject());
			pstmt.setString(2, article.getWr_content());
			pstmt.setInt(3, article.getWr_id());
			updateCount = pstmt.executeUpdate();
			
			//댓글 수정 : 제목 바뀌면 함께 수정
			String sql2 = "UPDATE support_board SET wr_subject=? WHERE wr_id=? and wr_co_id=1; ";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, "re:"+article.getWr_subject());
			pstmt.setInt(2, article.getWr_id());
			updateCount2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		System.out.println("updateCount: " + updateCount);
		System.out.println("updateCount2: " + updateCount2);
		return updateCount; //sql1의 구문 작업이 성공적으로 끝나면 sql2의 작업이 실패해도 서비스로 이동됨.
	}

	// 글 삭제 작업(글이 삭제되면 댓글도 함께 삭제)
	public int deleteArticle(int wr_id) {
		int deleteCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM support_board WHERE wr_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wr_id);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	
	//댓글 수정
	public int updateReArticle(qnaBean article_re) {
		System.out.println("qnaDao_updateRe");
		int updateCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE support_board set wr_subject=?,wr_content=?,wr_datetime=now() where wr_id=? and wr_co_id=1; ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article_re.getWr_subject());
			pstmt.setString(2, article_re.getWr_content());
			pstmt.setInt(3, article_re.getWr_id());
			
			System.out.println(article_re.getWr_subject());
			System.out.println(article_re.getWr_content());
			System.out.println(article_re.getMb_id());
			System.out.println(article_re.getWr_id());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateReArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateCount;
	}

}
