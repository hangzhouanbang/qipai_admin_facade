package com.anbang.qipai.admin.web.vo.agentsvo;

public enum RecordSummaryTexts {
	新推广员, 邀请玩家, 管理员调整, 积分兑换会员卡, 微信购买会员卡, 转赠下级, 上级转赠, 充值玩家VIP;

	public static String getSummaryText(String text) {
		switch (text) {
		case "new agent":
			return 新推广员.name();
		case "invite member":
			return 邀请玩家.name();
		case "admin adjust":
			return 管理员调整.name();
		case "score exchange clubcard":
			return 积分兑换会员卡.name();
		case "wechat buy clubcard":
			return 微信购买会员卡.name();
		case "give to junior":
			return 转赠下级.name();
		case "give by boss":
			return 上级转赠.name();
		case "recharge member":
			return 充值玩家VIP.name();
		default:
			return text;
		}
	}
}
