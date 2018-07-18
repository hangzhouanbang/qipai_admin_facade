package com.anbang.qipai.admin.plan.dao.agentdao;

import com.anbang.qipai.admin.plan.domain.agent.AgentOrder;

public interface AgentOrderDao {

	public AgentOrder findOrderByOut_trade_no(String out_trade_no);

	public void addOrder(AgentOrder order);

	public Boolean updateOrderStatus(String out_trade_no, String status);

	public Boolean updateTransaction_id(String out_trade_no, String transaction_id);

	public Boolean updateDeliveTime(String out_trade_no, Long deliveTime);
}
