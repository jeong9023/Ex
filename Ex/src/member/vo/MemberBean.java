package member.vo;

import java.sql.Timestamp;

public class MemberBean {
	private int no;
	private String memberID;
	private String password;
	private String nick;
	private String zip;
	private String address1;
	private String address2;
	private String phone;
	private int point;
	private int ticket;
	private String email;
	private int email_certify;
	private String email_certify2;
	private Timestamp datetime_reg;
	private Timestamp datetime_leave;
	private Timestamp datetime_ban;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEmail_certify() {
		return email_certify;
	}
	public void setEmail_certify(int email_certify) {
		this.email_certify = email_certify;
	}
	public String getEmail_certify2() {
		return email_certify2;
	}
	public void setEmail_certify2(String email_certify2) {
		this.email_certify2 = email_certify2;
	}
	public Timestamp getDatetime_reg() {
		return datetime_reg;
	}
	public void setDatetime_reg(Timestamp datetime_reg) {
		this.datetime_reg = datetime_reg;
	}
	public Timestamp getDatetime_leave() {
		return datetime_leave;
	}
	public void setDatetime_leave(Timestamp datetime_leave) {
		this.datetime_leave = datetime_leave;
	}
	public Timestamp getDatetime_ban() {
		return datetime_ban;
	}
	public void setDatetime_ban(Timestamp datetime_ban) {
		this.datetime_ban = datetime_ban;
	}
}
