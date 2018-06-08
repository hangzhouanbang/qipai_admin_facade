package com.anbang.qipai.admin.plan.domain;

public class Member {
	private String id;// 会员id
	private String nickname;// 会员昵称
	private String gender;// 会员性别:男:male,女:female
	private String headimgurl;// 头像url
	private String phone;// 会员手机
	private Integer score;// 会员积分
	private Integer gold;// 会员金币
	private Long createTime;// 注册时间
	private Long vipEndTime;// VIP时间

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getVipEndTime() {
		return vipEndTime;
	}

	public void setVipEndTime(Long vipEndTime) {
		this.vipEndTime = vipEndTime;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", nickname=" + nickname + ", gender=" + gender + ", headimgurl=" + headimgurl
				+ ", phone=" + phone + ", score=" + score + ", gold=" + gold + ", createTime=" + createTime
				+ ", vipEndTime=" + vipEndTime + "]";
	}

}
