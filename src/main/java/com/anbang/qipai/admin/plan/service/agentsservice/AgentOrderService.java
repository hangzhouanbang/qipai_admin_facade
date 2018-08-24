package com.anbang.qipai.admin.plan.service.agentsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentOrder;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentOrderDao;

@Service
public class AgentOrderService {

	@Autowired
	private AgentOrderDao orderDao;

	public void addOrder(AgentOrder order) {
		orderDao.addOrder(order);
	}
	
	public void orderFinished(String id, String transaction_id, String status, long deliveTime) {
		orderDao.orderFinished(id, transaction_id, status, deliveTime);
	}
}
