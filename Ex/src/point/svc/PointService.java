package point.svc;

import java.sql.Connection;

import point.dao.PointDAO;

import static common.JdbcUtil.*;

public class PointService {

	public boolean check(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPoint(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	public boolean checkTwo(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPointTwo(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	public boolean checkThree(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPointThree(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	public boolean checkFour(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPointFour(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	public boolean checkFive(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPointFive(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	public boolean checkSix(String mb_id) {
		boolean checkMember = false;
		
		Connection con = getConnection();
		PointDAO pointDAO = PointDAO.getInstance();
		pointDAO.setConnection(con);
		
		checkMember = pointDAO.setPointSix(mb_id);
		
		if(checkMember == true) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return checkMember;
	}
	
	
}
