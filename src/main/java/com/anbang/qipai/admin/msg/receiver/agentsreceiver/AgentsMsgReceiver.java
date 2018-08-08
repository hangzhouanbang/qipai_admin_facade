package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.agentschannel.AgentsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentDboService;
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
		if ("new agent".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.addAgentDbo(agent);
		}
		if ("apply pass".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.updateAgnetInfoAndAgentAuth(agent);
		}
		if ("update cost".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.updateAgentCost(agent.getId(), agent.getCost());
		}
		if ("update level".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.updateAgentDboLevel(agent.getId(), agent.getLevel());
		}
		if ("update boss".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.updateAgentDboBoss(agent.getId(), agent.getBossId(), agent.getBossName());
		}
		if ("remove boss".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.removeAgentDboBoss(agent.getId());
		}
		if ("ban agent".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.banAgentDboState(agent.getId());
		}
		if ("liberate agent".equals(msg)) {
			AgentDbo agent = gson.fromJson(json, AgentDbo.class);
			agentDboService.liberateAgentDboState(agent.getId());
		}
	}
}
