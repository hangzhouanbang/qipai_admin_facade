package com.anbang.qipai.admin.web.vo.agentsvo;

public class AgentInvitationVO {
	private String memberId;// 玩家id
	private String nickname;// 玩家昵称
	private int gold;// 玉石
	private long createTime;// 绑定时间

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

}
