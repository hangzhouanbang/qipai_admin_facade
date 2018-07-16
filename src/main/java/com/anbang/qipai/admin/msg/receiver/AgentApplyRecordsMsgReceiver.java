package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.AgentApplyRecordsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
import com.anbang.qipai.admin.plan.service.agentservice.AgentApplyRecordService;
import com.google.gson.Gson;

@EnableBinding(AgentApplyRecordsSink.class)
public class AgentApplyRecordsMsgReceiver {

	@Autowired
	private AgentApplyRecordService agentApplyRecordService;

	private Gson gson = new Gson();

	@StreamListener(AgentApplyRecordsSink.AGENTAPPLYRECORDS)
	public void recordMember(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		AgentApplyRecord record = gson.fromJson(json, AgentApplyRecord.class);
		if ("new applyrecord".equals(msg)) {
			agentApplyRecordService.addAgentApplyRecord(record);
		}
	}
}
