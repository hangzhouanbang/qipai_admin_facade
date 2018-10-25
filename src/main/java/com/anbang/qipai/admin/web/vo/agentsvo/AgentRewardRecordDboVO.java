package com.anbang.qipai.admin.web.vo.agentsvo;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.anbang.qipai.admin.plan.bean.agents.AgentRewardRecordDbo;

public class AgentRewardRecordDboVO extends AgentRewardRecordDbo {
	private Long startTime;
	private Long endTime;

	private String bossId;

	private String memberId;

	public Sort getSort() {
		Sort sort = new Sort(new Order(Direction.DESC, "accountingTime"));
		return sort;
	}

	public String getBossId() {
		return bossId;
	}

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
