package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.AgentRewardOrderSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentRewardOrder;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentRewardOrderService;
import com.google.gson.Gson;

@EnableBinding(AgentRewardOrderSink.class)
public class AgentRewardOrderMsgReceiver {
	@Autowired
	private AgentRewardOrderService agentRewardOrderService;

	private Gson gson = new Gson();

	@StreamListener(AgentRewardOrderSink.AGENTREWARDORDER)
	public void recordAgentRewardOrder(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("record order".equals(msg)) {
			AgentRewardOrder order = gson.fromJson(json, AgentRewardOrder.class);
			agentRewardOrderService.save(order);
		}
	}
}
