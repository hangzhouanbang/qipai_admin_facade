package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.AgentScoresSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentScoreRecordDboService;
import com.google.gson.Gson;

@EnableBinding(AgentScoresSink.class)
public class AgentScoresMsgReceiver {
	@Autowired
	private AgentScoreRecordDboService agentScoreRecordDboService;

	private Gson gson = new Gson();

	@StreamListener(AgentScoresSink.AGENTSCORES)
	public void recordAgent(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		AgentScoreRecordDbo dbo = gson.fromJson(json, AgentScoreRecordDbo.class);
		if ("accounting".equals(msg)) {
			agentScoreRecordDboService.addAgentScoreRecordDbo(dbo);
		}
	}
}
