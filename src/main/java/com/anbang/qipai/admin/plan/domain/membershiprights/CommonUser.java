package com.anbang.qipai.admin.plan.domain.membershiprights;

public class CommonUser {
	
	private String id;
	
	private Integer qdgold;//签到得金币数量
	
	private Integer fxintegral;//分享得积分数量
	
	private Integer goldfornewmember;//新用户注册赠送的金币数量
	
	private Integer fxgold;//分享得金币数量
	
	private Integer yqintegral;//邀请得积分数量
	
	private float speed;//积分增长速度
	
	private Integer addgold;//加入房间的金币价格
	
	private Integer numberroom;//加入房间数量
	
	private long experiencevalue;//经验值

	public Integer getGoldfornewmember() {
		return goldfornewmember;
	}

	public void setGoldfornewmember(Integer goldfornewmember) {
		this.goldfornewmember = goldfornewmember;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQdgold() {
		return qdgold;
	}

	public void setQdgold(Integer qdgold) {
		this.qdgold = qdgold;
	}

	public Integer getFxintegral() {
		return fxintegral;
	}

	public void setFxintegral(Integer fxintegral) {
		this.fxintegral = fxintegral;
	}

	public Integer getFxgold() {
		return fxgold;
	}

	public void setFxgold(Integer fxgold) {
		this.fxgold = fxgold;
	}

	public Integer getYqintegral() {
		return yqintegral;
	}

	public void setYqintegral(Integer yqintegral) {
		this.yqintegral = yqintegral;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public long getExperiencevalue() {
		return experiencevalue;
	}

	public void setExperiencevalue(long experiencevalue) {
		this.experiencevalue = experiencevalue;
	}

	public Integer getAddgold() {
		return addgold;
	}

	public void setAddgold(Integer addgold) {
		this.addgold = addgold;
	}

	public Integer getNumberroom() {
		return numberroom;
	}

	public void setNumberroom(Integer numberroom) {
		this.numberroom = numberroom;
	}
	
	
	
	

}
