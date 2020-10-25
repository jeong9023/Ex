package qna.vo;

import java.sql.Timestamp;

public class qnaBean {
	/*
	 * 게시판 글 하나의 정보를 저장하는 DTO 객체 = 자바빈(JavaBean)
	 * 
	 * DB 및 테이블 생성
	 */
	
	private int wr_no;
	private int wr_id;
	private int wr_co_id;
	private String wr_subject;
	private String wr_content;
	private Timestamp wr_datetime;
	private String mb_id;
	
	public int getWr_no() {
		return wr_no;
	}
	public void setWr_no(int wr_no) {
		this.wr_no = wr_no;
	}
	public int getWr_id() {
		return wr_id;
	}
	public void setWr_id(int wr_id) {
		this.wr_id = wr_id;
	}
	public int getWr_co_id() {
		return wr_co_id;
	}
	public void setWr_co_id(int wr_co_id) {
		this.wr_co_id = wr_co_id;
	}
	public String getWr_subject() {
		return wr_subject;
	}
	public void setWr_subject(String wr_subject) {
		this.wr_subject = wr_subject;
	}
	public String getWr_content() {
		return wr_content;
	}
	public void setWr_content(String wr_content) {
		this.wr_content = wr_content;
	}
	public Timestamp getWr_datetime() {
		return wr_datetime;
	}
	public void setWr_datetime(Timestamp wr_datetime) {
		this.wr_datetime = wr_datetime;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	
}
