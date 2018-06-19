package com.anbang.qipai.admin.plan.domain.record;

import com.dml.accounting.AccountingSummary;

public class MemberScoreRecordDbo {
	private String id;

	private String accountId;

	private String memberId;

	private Long accountingNo;

	private Integer accountingAmount;

	private Integer balanceAfter;

	private AccountingSummary summary;

	private Long accountingTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(Long accountingNo) {
		this.accountingNo = accountingNo;
	}

	public Integer getAccountingAmount() {
		return accountingAmount;
	}

	public void setAccountingAmount(Integer accountingAmount) {
		this.accountingAmount = accountingAmount;
	}

	public Integer getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(Integer balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public AccountingSummary getSummary() {
		return summary;
	}

	public void setSummary(AccountingSummary summary) {
		this.summary = summary;
	}

	public Long getAccountingTime() {
		return accountingTime;
	}

	public void setAccountingTime(Long accountingTime) {
		this.accountingTime = accountingTime;
	}

}
