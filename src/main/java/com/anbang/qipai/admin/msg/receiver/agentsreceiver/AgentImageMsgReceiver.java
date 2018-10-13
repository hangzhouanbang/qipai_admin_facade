package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.AgentImageSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentImageDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentImageDboService;
import com.google.gson.Gson;

@EnableBinding(AgentImageSink.class)
public class AgentImageMsgReceiver {

	@Autowired
	private AgentImageDboService agentImageDboService;

	private Gson gson = new Gson();

	@StreamListener(AgentImageSink.AGENTIMAGE)
	public void recordMember(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("new image".equals(msg)) {
			AgentImageDbo image = gson.fromJson(json, AgentImageDbo.class);
			agentImageDboService.addAgentImageDbo(image);
		}
		if ("remove image".equals(msg)) {
			String imageId = gson.fromJson(json, String.class);
			agentImageDboService.deleteAgentImageDboById(imageId);
		}
	}
}
