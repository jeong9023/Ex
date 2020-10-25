package market.vo;

import java.sql.Timestamp;

public class MarketCommentBean {
	private int lk_no;
	private int it_no;
	private String mb_id;
	private double lk_grade;
	private String lk_comment;
	private Timestamp lk_datetime;
	
	public int getLk_no() {
		return lk_no;
	}
	public void setLk_no(int lk_no) {
		this.lk_no = lk_no;
	}
	public int getIt_no() {
		return it_no;
	}
	public void setIt_no(int it_no) {
		this.it_no = it_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public double getLk_grade() {
		return lk_grade;
	}
	public void setLk_grade(double lk_grade) {
		this.lk_grade = lk_grade;
	}
	public String getLk_comment() {
		return lk_comment;
	}
	public void setLk_comment(String lk_comment) {
		this.lk_comment = lk_comment;
	}
	public Timestamp getLk_datetime() {
		return lk_datetime;
	}
	public void setLk_datetime(Timestamp lk_datetime) {
		this.lk_datetime = lk_datetime;
	}
	
}
