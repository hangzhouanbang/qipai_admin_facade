package com.anbang.qipai.admin.plan.dao.agentsdao;

import com.anbang.qipai.admin.plan.bean.agents.AgentOrder;

public interface AgentOrderDao {

	public void addOrder(AgentOrder order);

	void orderFinished(String id, String transaction_id, String status, long deliveTime);
}
