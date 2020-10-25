package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberBean;
import member.vo.LoginException;

import static common.JdbcUtil.*;

public class MemberDAO {	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static MemberDAO instance;
	
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		
		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 아이디로 회원정보 가져오기
	public MemberBean getMember(String memberID) {
		MemberBean mb = null;
		
		try {
			sql = "SELECT * FROM member WHERE mb_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setNo(rs.getInt("mb_no"));
				mb.setMemberID(rs.getString("mb_id"));
				mb.setPassword(rs.getString("mb_password"));
				mb.setNick(rs.getString("mb_nick"));
				mb.setZip(rs.getString("mb_zip"));
				mb.setAddress1(rs.getString("mb_address1"));
				mb.setAddress2(rs.getString("mb_address2"));
				mb.setPhone(rs.getString("mb_phone"));
				mb.setPoint(rs.getInt("mb_point"));
				mb.setTicket(rs.getInt("mb_ticket"));
				mb.setEmail(rs.getString("mb_email"));
				mb.setEmail_certify(rs.getInt("mb_email_certify"));
				mb.setEmail_certify2(rs.getString("mb_email_certify2"));
				mb.setDatetime_reg(rs.getTimestamp("mb_reg_datetime"));
				mb.setDatetime_leave(rs.getTimestamp("mb_leave_datetime"));
				mb.setDatetime_ban(rs.getTimestamp("mb_ban_datetime"));

				return mb;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return mb;
	}
	
	// 닉네임으로 회원정보 가져오기
	public MemberBean getMember2(String memberNick) {
		MemberBean mb = null;
		
		try {
			sql = "SELECT * FROM member WHERE mb_nick = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNick);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setNo(rs.getInt("mb_no"));
				mb.setMemberID(rs.getString("mb_id"));
				mb.setPassword(rs.getString("mb_password"));
				mb.setNick(rs.getString("mb_nick"));
				mb.setZip(rs.getString("mb_zip"));
				mb.setAddress1(rs.getString("mb_address1"));
				mb.setAddress2(rs.getString("mb_address2"));
				mb.setPhone(rs.getString("mb_phone"));
				mb.setPoint(rs.getInt("mb_point"));
				mb.setTicket(rs.getInt("mb_ticket"));
				mb.setEmail(rs.getString("mb_email"));
				mb.setEmail_certify(rs.getInt("mb_email_certify"));
				mb.setEmail_certify2(rs.getString("mb_email_certify2"));
				mb.setDatetime_reg(rs.getTimestamp("mb_reg_datetime"));
				mb.setDatetime_leave(rs.getTimestamp("mb_leave_datetime"));
				mb.setDatetime_ban(rs.getTimestamp("mb_ban_datetime"));
				
				return mb;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return mb;
	}
	
	// 이메일로 회원정보 가져오기
	public MemberBean getMember3(String memberEmail) {
		MemberBean mb = null;
		
		try {
			sql = "SELECT * FROM member WHERE mb_email = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setNo(rs.getInt("mb_no"));
				mb.setMemberID(rs.getString("mb_id"));
				mb.setPassword(rs.getString("mb_password"));
				mb.setNick(rs.getString("mb_nick"));
				mb.setZip(rs.getString("mb_zip"));
				mb.setAddress1(rs.getString("mb_address1"));
				mb.setAddress2(rs.getString("mb_address2"));
				mb.setPhone(rs.getString("mb_phone"));
				mb.setPoint(rs.getInt("mb_point"));
				mb.setTicket(rs.getInt("mb_ticket"));
				mb.setEmail(rs.getString("mb_email"));
				mb.setEmail_certify(rs.getInt("mb_email_certify"));
				mb.setEmail_certify2(rs.getString("mb_email_certify2"));
				mb.setDatetime_reg(rs.getTimestamp("mb_reg_datetime"));
				mb.setDatetime_leave(rs.getTimestamp("mb_leave_datetime"));
				mb.setDatetime_ban(rs.getTimestamp("mb_ban_datetime"));
				
				return mb;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return mb;
	}
	
	// 로그인 시 계정 정보 일치 여부 확인
	public boolean selectLoginMember(String memberID, String password) throws LoginException {
		boolean result = false;
		
		try {
			sql = "SELECT mb_password FROM member WHERE mb_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(password.equals(rs.getString("mb_password"))) {
					result = true;
				} else { // 비밀번호 불일치
					throw new LoginException("비밀번호가 틀렸습니다.");
				}
			} else { // 아이디 존재하지 않음
				throw new LoginException("아이디가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return result;
	}
	
	// 회원가입
	public int insertMember(MemberBean memberBean) {
		int isSuccess = 0;

		try {
			MemberBean mb = memberBean;
			
			// member 테이블 데이터 등록
			sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mb.getNo());
			pstmt.setString(2, mb.getMemberID());
			pstmt.setString(3, mb.getPassword());
			pstmt.setString(4, mb.getNick());
			pstmt.setString(5, mb.getZip());
			pstmt.setString(6, mb.getAddress1());
			pstmt.setString(7, mb.getAddress2());
			pstmt.setString(8, mb.getPhone());
			pstmt.setInt(9, mb.getPoint());
			pstmt.setInt(10, mb.getTicket());
			pstmt.setString(11, mb.getEmail());
			pstmt.setInt(12, mb.getEmail_certify());
			pstmt.setString(13, mb.getEmail_certify2());
			pstmt.setTimestamp(14, mb.getDatetime_reg());
			pstmt.setTimestamp(15, mb.getDatetime_leave());
			pstmt.setTimestamp(16, mb.getDatetime_ban());
			pstmt.executeUpdate();
			
			// market_profile 테이블 데이터 등록 (테이블 thumbnail이 테이블 market_profile의 mb_id를 참조하기 때문에 우선 순위로 데이터 등록)
			sql = "INSERT INTO market_profile (mb_id, mp_introduce) VALUES (?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMemberID());
			pstmt.setString(2, "");
			pstmt.executeUpdate();
			
			// thumbnail 테이블 데이터 등록
			sql = "INSERT INTO thumbnail (tn_name, tn_source_name, tn_flag, mb_id) VALUES (?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "default_profile_img.png");
			pstmt.setString(2, "default_profile_img.png");
			pstmt.setInt(3, 0);
			pstmt.setString(4, mb.getMemberID());
			isSuccess = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return isSuccess;
	}
	
	// 이메일 인증 확인 후 암호화 컬럼 초기화
	public int setMemberEmailConfirm(String registerID) {
		int isSuccess = 0;
		
		try {
			sql = "UPDATE member SET mb_email_certify = ?, mb_email_certify2 = ? WHERE mb_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, "");
			pstmt.setString(3, registerID);
			isSuccess = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return isSuccess;
	}
}
