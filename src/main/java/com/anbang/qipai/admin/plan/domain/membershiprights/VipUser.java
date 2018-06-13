package com.anbang.qipai.admin.plan.domain.membershiprights;

public class VipUser {
private String id;
	
	private Integer signGoldNumber;//普通用户签到得金币数量
	
	private Integer shareIntegralNumber;//分享得积分数量
	
	private Integer shareGoldNumber;//分享得金币数量
	
	private Integer inviteIntegralNumber;//邀请得积分数量
	
	private float vipGrowIntegralSpeed;//会员积分增长速度
	
	private float vipGrowGradeSpeed;//会员等级增长速度
	
	private Integer createRoomGoldPrice;//创建房间的金币价格
	private Integer addRoomGoldPrice;//加入房间的金币价格
	
	private Integer vipMemberRoomsCount;//vip保存房间数量
	
	private Integer vipMemberRoomsAliveHours;//会员房间存活小时数

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

	public Integer getCreateRoomGoldPrice() {
		return createRoomGoldPrice;
	}

	public void setCreateRoomGoldPrice(Integer createRoomGoldPrice) {
		this.createRoomGoldPrice = createRoomGoldPrice;
	}

	public Integer getAddRoomGoldPrice() {
		return addRoomGoldPrice;
	}

	public void setAddRoomGoldPrice(Integer addRoomGoldPrice) {
		this.addRoomGoldPrice = addRoomGoldPrice;
	}

	public Integer getVipMemberRoomsCount() {
		return vipMemberRoomsCount;
	}

	public void setVipMemberRoomsCount(Integer vipMemberRoomsCount) {
		this.vipMemberRoomsCount = vipMemberRoomsCount;
	}

	public Integer getVipMemberRoomsAliveHours() {
		return vipMemberRoomsAliveHours;
	}

	public void setVipMemberRoomsAliveHours(Integer vipMemberRoomsAliveHours) {
		this.vipMemberRoomsAliveHours = vipMemberRoomsAliveHours;
	}
	

	
}