package com.anbang.qipai.admin.web.vo.membersvo;

import com.anbang.qipai.admin.plan.domain.members.MemberOrder;

public class MemberOrderVO extends MemberOrder {
	private Long startTime;
	private Long endTime;

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
