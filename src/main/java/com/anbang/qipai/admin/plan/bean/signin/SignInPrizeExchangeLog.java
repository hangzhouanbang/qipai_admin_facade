package com.anbang.qipai.admin.plan.bean.signin;

public class SignInPrizeExchangeLog {//签到奖品兑奖纪录
	private String id;
	private SignInPrizeLog signInPrizeLog;
	private String phone;
	private String deliveryName;
	private String address;
	private long rewardTime;//兑奖时间
	private String issue;//是否发放,0：未发放，1、已发放
	private long issueTime;//发放时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SignInPrizeLog getSignInPrizeLog() {
		return signInPrizeLog;
	}
	public void setSignInPrizeLog(SignInPrizeLog signInPrizeLog) {
		this.signInPrizeLog = signInPrizeLog;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getRewardTime() {
		return rewardTime;
	}
	public void setRewardTime(Long rewardTime) {
		this.rewardTime = rewardTime;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public long getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Long issueTime) {
		this.issueTime = issueTime;
	}
	
}
