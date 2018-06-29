package com.anbang.qipai.admin.plan.domain;

public class GameDataReport {
	private String id;
	private String game;// 游戏名
	private Long date;// 日期
	private Integer currentMember;// 进入游戏的当日会员人数
	private Integer gameNum;// 游戏总局数
	private Integer loginMember;// 独立玩家

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(Integer currentMember) {
		this.currentMember = currentMember;
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

}
