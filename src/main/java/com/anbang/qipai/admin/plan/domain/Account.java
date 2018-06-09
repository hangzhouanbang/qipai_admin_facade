package com.anbang.qipai.admin.plan.domain;

public class Account {

	private String id;// 账单id
	private String payerId;// 支付人id
	private String product;// 商品
	private Integer number;// 购买数量
	private Integer amount;// 总额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", payerId=" + payerId + ", product=" + product + ", number=" + number
				+ ", amount=" + amount + "]";
	}

}
