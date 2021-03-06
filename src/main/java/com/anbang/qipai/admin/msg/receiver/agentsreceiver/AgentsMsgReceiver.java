package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.AgentsSink;
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
		AgentDbo agent = gson.fromJson(json, AgentDbo.class);
		if ("new agent".equals(msg)) {
			agentDboService.addAgentDbo(agent);
		}
		if ("apply pass".equals(msg)) {
			agentDboService.updateAgnetInfoAndAgentAuth(agent);
		}
		if ("update cost".equals(msg)) {
			agentDboService.updateAgentCost(agent.getId(), agent.getCost());
		}
		if ("update agent type".equals(msg)) {
			agentDboService.updateAgentDboType(agent.getId(), agent.getAgentType());
		}
		if ("update boss".equals(msg)) {
			agentDboService.updateAgentDboBoss(agent.getId(), agent.getBossId(), agent.getBossName());
		}
		if ("remove boss".equals(msg)) {
			agentDboService.removeAgentDboBoss(agent.getId());
		}
		if ("update agent juniornum".equals(msg)) {
			agentDboService.updateAgentJuniorNum(agent.getId(), agent.getJuniorNum());
		}
		if ("update agent invitemembernum".equals(msg)) {
			agentDboService.updateAgentInviteMemberNum(agent.getId(), agent.getInviteMemberNum());
		}
		if ("ban agent".equals(msg)) {
			agentDboService.updateAgentDboState(agent.getId(), agent.getState());
		}
		if ("liberate agent".equals(msg)) {
			agentDboService.updateAgentDboState(agent.getId(), agent.getState());
		}
	}
}
