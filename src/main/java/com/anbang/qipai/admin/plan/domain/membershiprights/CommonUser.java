package com.anbang.qipai.admin.plan.domain.membershiprights;

public class CommonUser {
	
	private String id;
	
	private Integer signGoldNumber;//普通用户签到得金币数量
	
	private Integer shareIntegralNumber;//分享得积分数量
	
	private Integer goldForNewNember;//新用户注册赠送的金币数量
	
	private Integer shareGoldNumber;//分享得金币数量
	
	private Integer inviteIntegralNumber;//邀请得积分数量
	
	private float planGrowIntegralSpeed;//普通用户积分增长速度
	
	private Integer planMemberCreateRoomDailyGoldPrice;//创建房间的金币价格
	private Integer planMemberaddRoomDailyGoldPrice;//加入房间的金币价格
	
	private Integer planMemberRoomsCount;//普通保存房间数量
	
	private Integer planMemberMaxCreateRoomDaily;//普通用户创建vip房间的上限
	
	private Integer planMemberRoomsAliveHours;//普通房间存活小时数
	
	public float getPlanGrowIntegralSpeed() {
		return planGrowIntegralSpeed;
	}
	public void setPlanGrowIntegralSpeed(float planGrowIntegralSpeed) {
		this.planGrowIntegralSpeed = planGrowIntegralSpeed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getSignGoldNumber() {
		return signGoldNumber;
	}
	public void setSignGoldNumber(Integer signGoldNumber) {
		this.signGoldNumber = signGoldNumber;
	}
	public Integer getShareIntegralNumber() {
		return shareIntegralNumber;
	}
	public void setShareIntegralNumber(Integer shareIntegralNumber) {
		this.shareIntegralNumber = shareIntegralNumber;
	}
	public Integer getGoldForNewNember() {
		return goldForNewNember;
	}
	public void setGoldForNewNember(Integer goldForNewNember) {
		this.goldForNewNember = goldForNewNember;
	}
	public Integer getShareGoldNumber() {
		return shareGoldNumber;
	}
	public void setShareGoldNumber(Integer shareGoldNumber) {
		this.shareGoldNumber = shareGoldNumber;
	}
	public Integer getInviteIntegralNumber() {
		return inviteIntegralNumber;
	}
	public void setInviteIntegralNumber(Integer inviteIntegralNumber) {
		this.inviteIntegralNumber = inviteIntegralNumber;
	}
	public Integer getPlanMemberRoomsCount() {
		return planMemberRoomsCount;
	}
	public void setPlanMemberRoomsCount(Integer planMemberRoomsCount) {
		this.planMemberRoomsCount = planMemberRoomsCount;
	}
	public Integer getPlanMemberRoomsAliveHours() {
		return planMemberRoomsAliveHours;
	}
	public void setPlanMemberRoomsAliveHours(Integer planMemberRoomsAliveHours) {
		this.planMemberRoomsAliveHours = planMemberRoomsAliveHours;
	}
	public Integer getPlanMemberCreateRoomDailyGoldPrice() {
		return planMemberCreateRoomDailyGoldPrice;
	}
	public void setPlanMemberCreateRoomDailyGoldPrice(Integer planMemberCreateRoomDailyGoldPrice) {
		this.planMemberCreateRoomDailyGoldPrice = planMemberCreateRoomDailyGoldPrice;
	}
	public Integer getPlanMemberaddRoomDailyGoldPrice() {
		return planMemberaddRoomDailyGoldPrice;
	}
	public void setPlanMemberaddRoomDailyGoldPrice(Integer planMemberaddRoomDailyGoldPrice) {
		this.planMemberaddRoomDailyGoldPrice = planMemberaddRoomDailyGoldPrice;
	}
	public Integer getPlanMemberMaxCreateRoomDaily() {
		return planMemberMaxCreateRoomDaily;
	}
	public void setPlanMemberMaxCreateRoomDaily(Integer planMemberMaxCreateRoomDaily) {
		this.planMemberMaxCreateRoomDaily = planMemberMaxCreateRoomDaily;
	}

	
	
}