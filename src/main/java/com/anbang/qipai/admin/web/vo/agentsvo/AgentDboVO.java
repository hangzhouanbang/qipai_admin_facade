package com.anbang.qipai.admin.web.vo.agentsvo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.anbang.qipai.admin.plan.bean.agents.AgentDbo;

public class AgentDboVO extends AgentDbo {
	private Long startTime;
	private Long endTime;
	private String levelSort;
	private String createTimeSort;
	private String stateSort;

	public Sort getSort() {
		List<Order> orderList = new ArrayList<>();
		if ("ASC".equals(levelSort)) {
			orderList.add(new Order(Direction.ASC, "level"));
		} else if ("DESC".equals(levelSort)) {
			orderList.add(new Order(Direction.DESC, "level"));
		}
		if ("ASC".equals(createTimeSort)) {
			orderList.add(new Order(Direction.ASC, "createTime"));
		} else if ("DESC".equals(createTimeSort)) {
			orderList.add(new Order(Direction.DESC, "createTime"));
		}
		if ("ASC".equals(stateSort)) {
			orderList.add(new Order(Direction.ASC, "state"));
		} else if ("DESC".equals(stateSort)) {
			orderList.add(new Order(Direction.DESC, "state"));
		}
		if (!orderList.isEmpty()) {
			Sort sort = new Sort(orderList);
			return sort;
		}
		return null;
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

	public String getLevelSort() {
		return levelSort;
	}

	public void setLevelSort(String levelSort) {
		this.levelSort = levelSort;
	}

	public String getCreateTimeSort() {
		return createTimeSort;
	}

	public void setCreateTimeSort(String createTimeSort) {
		this.createTimeSort = createTimeSort;
	}

	public String getStateSort() {
		return stateSort;
	}

	public void setStateSort(String stateSort) {
		this.stateSort = stateSort;
	}

}
