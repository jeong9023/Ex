package market.vo;

public class MarketBean {
//	CREATE TABLE IF NOT EXISTS `ddang`.`market_profile` (
//			  `mp_no` INT NOT NULL AUTO_INCREMENT COMMENT '상점 번호',
//			  `mb_id` VARCHAR(30) NOT NULL COMMENT '회원 아이디',
//			  `mp_introduce` TEXT NOT NULL,

//	CREATE TABLE IF NOT EXISTS `ddang`.`thumbnail` (
//			  `tn_no` INT NOT NULL AUTO_INCREMENT COMMENT '썸네일 번호',
//			  `tn_name` VARCHAR(255) NOT NULL COMMENT '사용자가 썸네일 등록 시 파일명',
//			  `tn_source_name` VARCHAR(255) NOT NULL COMMENT '서버에 저장된 썸네일 파일명',
//			  `mb_id` VARCHAR(20) NOT NULL COMMENT '회원 아이디(상점용)',
//			  `it_no` INT NULL COMMENT '상품 번호(상품용)',
	private int mp_no;
	private String mb_id;
	private String mp_introduce;
	private int tn_no;
	private String tn_name;
	private String tn_source_name;
	
	
	public int getMp_no() {
		return mp_no;
	}
	public void setMp_no(int mp_no) {
		this.mp_no = mp_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMp_introduce() {
		return mp_introduce;
	}
	public void setMp_introduce(String mp_introduce) {
		this.mp_introduce = mp_introduce;
	}
	public int getTn_no() {
		return tn_no;
	}
	public void setTn_no(int tn_no) {
		this.tn_no = tn_no;
	}
	public String getTn_name() {
		return tn_name;
	}
	public void setTn_name(String tn_name) {
		this.tn_name = tn_name;
	}
	public String getTn_source_name() {
		return tn_source_name;
	}
	public void setTn_source_name(String tn_source_name) {
		this.tn_source_name = tn_source_name;
	}

	
	
}
