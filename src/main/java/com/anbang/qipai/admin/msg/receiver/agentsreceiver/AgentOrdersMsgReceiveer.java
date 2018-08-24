package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.agentschannel.AgentOrdersSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentOrder;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentOrderService;
import com.google.gson.Gson;

@EnableBinding(AgentOrdersSink.class)
public class AgentOrdersMsgReceiveer {
	@Autowired
	private AgentOrderService agentOrderService;

	private Gson gson = new Gson();

	@StreamListener(AgentOrdersSink.agentOrders)
	public void recordOrder(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		AgentOrder order = gson.fromJson(json, AgentOrder.class);
		if ("new order".equals(mo.getMsg())) {
			agentOrderService.addOrder(order);
		}
		if ("trade over".equals(mo.getMsg())) {
			agentOrderService.orderFinished(order.getId(), order.getTransaction_id(), order.getStatus(), order.getDeliveTime());
		}
	}
}
