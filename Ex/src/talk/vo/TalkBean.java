package talk.vo;

import java.sql.Timestamp;

public class TalkBean {
	private int no;
	private int itemNumber;
	private String recvMemberID;
	private String sendMemberID;
	private String sendMemberNick;
	private Timestamp sendDatetime;
	private String sendDatetimeToString;
	private String content;
	private String profileImgName;
	private String itemName;
	private String itemImgName;
	private int itemPriceStart;
	private String marketIntroduce;
	private int enterCount;
	private int successfulCount;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getRecvMemberID() {
		return recvMemberID;
	}
	public void setRecvMemberID(String recvMemberID) {
		this.recvMemberID = recvMemberID;
	}
	public String getSendMemberID() {
		return sendMemberID;
	}
	public void setSendMemberID(String sendMemberID) {
		this.sendMemberID = sendMemberID;
	}
	public String getSendMemberNick() {
		return sendMemberNick;
	}
	public void setSendMemberNick(String sendMemberNick) {
		this.sendMemberNick = sendMemberNick;
	}
	public Timestamp getSendDatetime() {
		return sendDatetime;
	}
	public void setSendDatetime(Timestamp sendDatetime) {
		this.sendDatetime = sendDatetime;
	}
	public String getSendDatetimeToString() {
		return sendDatetimeToString;
	}
	public void setSendDatetimeToString(String sendDatetimeToString) {
		this.sendDatetimeToString = sendDatetimeToString;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProfileImgName() {
		return profileImgName;
	}
	public void setProfileImgName(String profileImgName) {
		this.profileImgName = profileImgName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemImgName() {
		return itemImgName;
	}
	public void setItemImgName(String itemImgName) {
		this.itemImgName = itemImgName;
	}
	public String getMarketIntroduce() {
		return marketIntroduce;
	}
	public void setMarketIntroduce(String marketIntroduce) {
		this.marketIntroduce = marketIntroduce;
	}
	public int getEnterCount() {
		return enterCount;
	}
	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}
	public int getSuccessfulCount() {
		return successfulCount;
	}
	public void setSuccessfulCount(int successfulCount) {
		this.successfulCount = successfulCount;
	}
	public int getItemPriceStart() {
		return itemPriceStart;
	}
	public void setItemPriceStart(int itemPriceStart) {
		this.itemPriceStart = itemPriceStart;
	}
}
