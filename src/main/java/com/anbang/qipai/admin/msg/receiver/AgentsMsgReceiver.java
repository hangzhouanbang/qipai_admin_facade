package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.AgentsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentDboService;
import com.google.gson.Gson;

@EnableBinding(AgentsSink.class)
public class AgentsMsgReceiver {

	@Autowired
	private AgentDboService agentDboService;

	private Gson gson = new Gson();

	@StreamListener(AgentsSink.AGENTS)
	public void recordAgent(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		AgentDbo agent = gson.fromJson(json, AgentDbo.class);
		if ("new agent".equals(msg)) {
			agentDboService.addAgentDbo(agent);
		}
	}
}
