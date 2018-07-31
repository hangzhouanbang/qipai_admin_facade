package com.anbang.qipai.admin.plan.domain.agents;

public class AgentClubCard {
	private String id;
	private String product;// 商品名称
	private String productPic;// ICON图
	private Integer number;
	private Integer repertory;// 库存数量
	private Integer remain;// 剩余
	private String payType;// 支付方式
	private Double price;// 价格
	private Integer weight;// 权重
	private Boolean sale;// 是否可用

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getRepertory() {
		return repertory;
	}

	public void setRepertory(Integer repertory) {
		this.repertory = repertory;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getSale() {
		return sale;
	}

	public void setSale(Boolean sale) {
		this.sale = sale;
	}

	public Integer getRemain() {
		return remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

}
