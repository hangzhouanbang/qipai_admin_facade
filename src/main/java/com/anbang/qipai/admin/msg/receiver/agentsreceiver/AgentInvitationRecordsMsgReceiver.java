package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.agentschannel.AgentInvitationRecordsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agents.AgentInvitationRecord;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentInvitationRecordService;
import com.google.gson.Gson;

@EnableBinding(AgentInvitationRecordsSink.class)
public class AgentInvitationRecordsMsgReceiver {
	@Autowired
	private AgentInvitationRecordService agentInvitationRecordService;

	private Gson gson = new Gson();

	@StreamListener(AgentInvitationRecordsSink.AGENTINVITATIONRECORDS)
	public void recordOrder(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		AgentInvitationRecord record = gson.fromJson(json, AgentInvitationRecord.class);
		if ("new invitation record".equals(mo.getMsg())) {
			agentInvitationRecordService.addAgentInvitationRecord(record);
		}
	}
}
