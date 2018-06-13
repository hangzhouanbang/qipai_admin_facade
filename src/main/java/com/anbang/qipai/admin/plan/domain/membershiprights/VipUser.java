package com.anbang.qipai.admin.plan.domain.membershiprights;

public class VipUser {
private String id;
	
	private int signGoldNumber;//普通用户签到得金币数量
	
	private int shareIntegralNumber;//分享得积分数量
	
	private int shareGoldNumber;//分享得金币数量
	
	private int inviteIntegralNumber;//邀请得积分数量
	
	private float vipGrowIntegralSpeed;//会员积分增长速度
	
	private float vipGrowGradeSpeed;//会员等级增长速度
	
	private int createRoomGoldPrice;//创建房间的金币价格
	private int addRoomGoldPrice;//加入房间的金币价格
	
	private int vipMemberRoomsCount;//vip保存房间数量
	
	private int vipMemberRoomsAliveHours;//会员房间存活小时数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSignGoldNumber() {
		return signGoldNumber;
	}

	public void setSignGoldNumber(int signGoldNumber) {
		this.signGoldNumber = signGoldNumber;
	}

	public int getShareIntegralNumber() {
		return shareIntegralNumber;
	}

	public void setShareIntegralNumber(int shareIntegralNumber) {
		this.shareIntegralNumber = shareIntegralNumber;
	}

	public int getShareGoldNumber() {
		return shareGoldNumber;
	}

	public void setShareGoldNumber(int shareGoldNumber) {
		this.shareGoldNumber = shareGoldNumber;
	}

	public int getInviteIntegralNumber() {
		return inviteIntegralNumber;
	}

	public void setInviteIntegralNumber(int inviteIntegralNumber) {
		this.inviteIntegralNumber = inviteIntegralNumber;
	}

	public float getVipGrowIntegralSpeed() {
		return vipGrowIntegralSpeed;
	}

	public void setVipGrowIntegralSpeed(float vipGrowIntegralSpeed) {
		this.vipGrowIntegralSpeed = vipGrowIntegralSpeed;
	}

	public float getVipGrowGradeSpeed() {
		return vipGrowGradeSpeed;
	}

	public void setVipGrowGradeSpeed(float vipGrowGradeSpeed) {
		this.vipGrowGradeSpeed = vipGrowGradeSpeed;
	}

	public int getCreateRoomGoldPrice() {
		return createRoomGoldPrice;
	}

	public void setCreateRoomGoldPrice(int createRoomGoldPrice) {
		this.createRoomGoldPrice = createRoomGoldPrice;
	}

	public int getAddRoomGoldPrice() {
		return addRoomGoldPrice;
	}

	public void setAddRoomGoldPrice(int addRoomGoldPrice) {
		this.addRoomGoldPrice = addRoomGoldPrice;
	}

	public int getVipMemberRoomsCount() {
		return vipMemberRoomsCount;
	}

	public void setVipMemberRoomsCount(int vipMemberRoomsCount) {
		this.vipMemberRoomsCount = vipMemberRoomsCount;
	}

	public int getVipMemberRoomsAliveHours() {
		return vipMemberRoomsAliveHours;
	}

	public void setVipMemberRoomsAliveHours(int vipMemberRoomsAliveHours) {
		this.vipMemberRoomsAliveHours = vipMemberRoomsAliveHours;
	}

	
}