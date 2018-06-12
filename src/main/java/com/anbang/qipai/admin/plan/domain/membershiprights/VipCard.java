package com.anbang.qipai.admin.plan.domain.membershiprights;

public class VipCard {
	
	private String id;
	
	private String vipcardtype;//会员卡类型
	
	private Integer buymoney;//购买金额
	
	private Integer buyintegral;//购买获得积分数量
	
	private Integer buygold;//购买获得金币数量

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBuymoney() {
		return buymoney;
	}

	public void setBuymoney(Integer buymoney) {
		this.buymoney = buymoney;
	}

	public Integer getBuyintegral() {
		return buyintegral;
	}

	public void setBuyintegral(Integer buyintegral) {
		this.buyintegral = buyintegral;
	}

	public Integer getBuygold() {
		return buygold;
	}

	public void setBuygold(Integer buygold) {
		this.buygold = buygold;
	}

	public String getVipcardtype() {
		return vipcardtype;
	}

	public void setVipcardtype(String vipcardtype) {
		this.vipcardtype = vipcardtype;
	}
	

}
