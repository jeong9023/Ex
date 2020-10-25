package report.vo;

import java.sql.Timestamp;

public class ReportBean {
	private int rp_no;
	private String mb_id;
	private String rp_suspect_mb_id;
	private String rp_content;
	private int rp_flag;
	private int rp_feedback;
	private int it_no;
	private String inquiry;
	private int statsCount;
	private int statsTwoCount;
	private int statsThreeCount;
	private Timestamp mb_ban_datetime;
	
	public Timestamp getMb_ban_datetime() {
		return mb_ban_datetime;
	}
	public void setMb_ban_datetime(Timestamp mb_ban_datetime) {
		this.mb_ban_datetime = mb_ban_datetime;
	}
	public int getStatsThreeCount() {
		return statsThreeCount;
	}
	public void setStatsThreeCount(int statsThreeCount) {
		this.statsThreeCount = statsThreeCount;
	}
	public ReportBean() {}
	public ReportBean(int statsCount, int statsTwoCount, int statsThreeCount ) {
		super();
		this.statsCount = statsCount;
		this.statsTwoCount = statsTwoCount;
		this.statsThreeCount = statsThreeCount;
	}
	
	public int getStatsTwoCount() {
		return statsTwoCount;
	}
	public void setStatsTwoCount(int statsTwoCount) {
		this.statsTwoCount = statsTwoCount;
	}
	public int getStatsCount() {
		return statsCount;
	}
	public void setStatsCount(int statsCount) {
		this.statsCount = statsCount;
	}
	public String getInquiry() {
		return inquiry;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public String getRp_suspect_mb_id() {
		return rp_suspect_mb_id;
	}
	public void setRp_suspect_mb_id(String rp_suspect_mb_id) {
		this.rp_suspect_mb_id = rp_suspect_mb_id;
	}
	public int getRp_no() {
		return rp_no;
	}
	public void setRp_no(int rp_no) {
		this.rp_no = rp_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getRp_content() {
		return rp_content;
	}
	public void setRp_content(String rp_content) {
		this.rp_content = rp_content;
	}
	public int getRp_flag() {
		return rp_flag;
	}
	public void setRp_flag(int rp_flag) {
		this.rp_flag = rp_flag;
	}
	public int getRp_feedback() {
		return rp_feedback;
	}
	public void setRp_feedback(int rp_feedback) {
		this.rp_feedback = rp_feedback;
	}
	public int getIt_no() {
		return it_no;
	}
	public void setIt_no(int it_no) {
		this.it_no = it_no;
	}
	

}
