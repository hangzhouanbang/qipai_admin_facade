package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.AgentTypeSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentType;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentTypeService;
import com.google.gson.Gson;

@EnableBinding(AgentTypeSink.class)
public class AgentTypeMsgReceiver {
	@Autowired
	private AgentTypeService agentTypeService;

	private Gson gson = new Gson();

	@StreamListener(AgentTypeSink.AGENTTYPE)
	public void recordAgent(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("new agent type".equals(msg)) {
			AgentType type = gson.fromJson(json, AgentType.class);
			agentTypeService.save(type);
		}
		if ("update agent type".equals(msg)) {
			AgentType type = gson.fromJson(json, AgentType.class);
			agentTypeService.updateAgentType(type);
		}
		if ("remove agent type".equals(msg)) {
			String[] typeIds = gson.fromJson(json, String[].class);
			agentTypeService.removeByIds(typeIds);
		}
	}
}
