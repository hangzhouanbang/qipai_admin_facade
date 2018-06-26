package com.anbang.qipai.admin.plan.domain;

public class PlatformReport {
	private String id;
	private Long date;// 日期
	private Integer newMember;// 新增注册量
	private Integer currentMember;// 当日会员人数
	private Double cost;// 消费金额
	private Integer gameNum;// 游戏总局数
	private Integer loginMember;// 独立玩家
	private Integer remainSecond;// 次日留存
	private Integer remainThird;// 三日留存
	private Integer remainSeventh;// 七日留存
	private Integer remainMonth;// 30日以外留存

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getNewMember() {
		return newMember;
	}

	public void setNewMember(Integer newMember) {
		this.newMember = newMember;
	}

	public Integer getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(Integer currentMember) {
		this.currentMember = currentMember;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getGameNum() {
		return gameNum;
	}

	public void setGameNum(Integer gameNum) {
		this.gameNum = gameNum;
	}

	public Integer getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(Integer loginMember) {
		this.loginMember = loginMember;
	}

	public Integer getRemainSecond() {
		return remainSecond;
	}

	public void setRemainSecond(Integer remainSecond) {
		this.remainSecond = remainSecond;
	}

	public Integer getRemainThird() {
		return remainThird;
	}

	public void setRemainThird(Integer remainThird) {
		this.remainThird = remainThird;
	}

	public Integer getRemainSeventh() {
		return remainSeventh;
	}

	public void setRemainSeventh(Integer remainSeventh) {
		this.remainSeventh = remainSeventh;
	}

	public Integer getRemainMonth() {
		return remainMonth;
	}

	public void setRemainMonth(Integer remainMonth) {
		this.remainMonth = remainMonth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
