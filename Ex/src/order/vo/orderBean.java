package order.vo;

import java.sql.Timestamp;

public class orderBean {
	private String it_category;
	private Timestamp tr_datetime;
	private String tr_datetime2;
	private String mb_id;
	private String tr_flag;
	private int it_successful_price;
	private String it_name;
	private String mb_id2;
	private String it_thumbnail;
	private int it_no;
	
	public String getIt_category() {
		return it_category;
	}
	public void setIt_category(String it_category) {
		this.it_category = it_category;
	}
	public Timestamp getTr_datetime() {
		return tr_datetime;
	}
	public void setTr_datetime(Timestamp tr_datetime) {
		this.tr_datetime = tr_datetime;
	}
	public void setTr_datetime(String date) {
		this.tr_datetime2 = date;
	}
	public String getTr_datetime2() {
		return tr_datetime2;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getTr_flag() {
		return tr_flag;
	}
	public void setTr_flag(String tr_flag) {
		this.tr_flag = tr_flag;
	}
	public int getIt_successful_price() {
		return it_successful_price;
	}
	public void setIt_successful_price(int it_successful_price) {
		this.it_successful_price = it_successful_price;
	}
	public String getIt_name() {
		return it_name;
	}
	public void setIt_name(String it_name) {
		this.it_name = it_name;
	}
	public String getMb_id2() {
		return mb_id2;
	}
	public void setMb_id2(String mb_id2) {
		this.mb_id2 = mb_id2;
	}
	public String getIt_thumbnail() {
		return it_thumbnail;
	}
	public void setIt_thumbnail(String it_thumbnail) {
		this.it_thumbnail = it_thumbnail;
	}
	public int getIt_no() {
		return it_no;
	}
	public void setIt_no(int it_no) {
		this.it_no = it_no;
	}
	
	
	
}
