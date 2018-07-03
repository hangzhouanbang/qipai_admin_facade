package com.anbang.qipai.admin.plan.domain.mail;

public class MailList {
	
	private String memberId;//会员编号
	
	private String receive;//是否领取
	
	private long rewardTime;//领取时间
	
	private SystemMail systemMail;//邮件内容

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public long getRewardTime() {
		return rewardTime;
	}

	public void setRewardTime(long rewardTime) {
		this.rewardTime = rewardTime;
	}

	public SystemMail getSystemMail() {
		return systemMail;
	}

	public void setSystemMail(SystemMail systemMail) {
		this.systemMail = systemMail;
	}
	
	

}
