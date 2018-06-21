package com.anbang.qipai.admin.web.vo;

import com.anbang.qipai.admin.plan.domain.record.Order;

public class OrderVO extends Order {
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

	@Override
	public String toString() {
		return "OrderVO [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
