package com.anbang.qipai.admin.plan.service.agentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentOrderDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentOrder;

@Service
public class AgentOrderService {

	@Autowired
	private AgentOrderDao orderDao;

	public void addOrder(AgentOrder order) {
		orderDao.addOrder(order);
	}

	public AgentOrder findOrderByOut_trade_no(String out_trade_no) {
		return orderDao.findOrderByOut_trade_no(out_trade_no);
	}

	public Boolean updateOrderStatus(String out_trade_no, String status) {
		return orderDao.updateOrderStatus(out_trade_no, status);
	}

	public Boolean updateTransaction_id(String out_trade_no, String transaction_id) {
		return orderDao.updateTransaction_id(out_trade_no, transaction_id);
	}

	public Boolean updateDeliveTime(String out_trade_no, Long deliveTime) {
		return orderDao.updateDeliveTime(out_trade_no, deliveTime);
	}
}
