package com.anbang.qipai.admin.plan.bean.agents;

public class AgentClubCard {
	private String id;
	private String product;// 商品名称
	private String productPic;// ICON图
	private int number;
	private int repertory;// 库存数量
	private int remain;// 剩余
	private String payType;// 支付方式
	private double firstPrice;// 一级价格
	private double secordPrice;// 二级价格
	private int weight;// 权重
	private boolean sale;// 是否可用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRepertory() {
		return repertory;
	}

	public void setRepertory(int repertory) {
		this.repertory = repertory;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getSecordPrice() {
		return secordPrice;
	}

	public void setSecordPrice(double secordPrice) {
		this.secordPrice = secordPrice;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

}
