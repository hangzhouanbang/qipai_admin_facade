package com.anbang.qipai.admin.plan.domain.agent;

public class AgentDbo {
	private String id;// 推广员id
	private String nickname;// 推广员昵称
	private String gender;// 推广员性别:男:male,女:female
	private String headimgurl;// 头像url
	private String phone;// 推广员手机
	private String userName;// 姓名
	private String idCard;// 推广员身份证号
	private String frontUrl;// 身份证正面照片
	private String reverseUrl;// 身份证反面照片
	private String bossId;// 上级id
	private String bossName;// 上级昵称
	private Integer level;// 推广等级
	private Long createTime;
	private Boolean agentAuth;// 是否通过推广员申请
	private String state;// 正常,封停
	private String invitationCode;// 邀请码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getReverseUrl() {
		return reverseUrl;
	}

	public void setReverseUrl(String reverseUrl) {
		this.reverseUrl = reverseUrl;
	}

	public String getBossId() {
		return bossId;
	}

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Boolean getAgentAuth() {
		return agentAuth;
	}

	public void setAgentAuth(Boolean agentAuth) {
		this.agentAuth = agentAuth;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
