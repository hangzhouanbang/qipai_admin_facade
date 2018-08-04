package com.anbang.qipai.admin.web.vo.agentsvo;

import com.anbang.qipai.admin.plan.bean.agents.AgentScoreRecordDbo;

public class AgentScoreRecordDboVO extends AgentScoreRecordDbo {
	private Long startTime;
	private Long endTime;
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
