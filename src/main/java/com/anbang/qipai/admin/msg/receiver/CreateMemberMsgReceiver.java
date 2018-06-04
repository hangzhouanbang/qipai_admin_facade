package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.anbang.qipai.admin.msg.channel.CreateMemberSink;
import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
import com.anbang.qipai.admin.plan.service.CreateMemberService;

import net.sf.json.JSONObject;

@EnableBinding(CreateMemberSink.class)
public class CreateMemberMsgReceiver {
	
	@Autowired
	private CreateMemberService createMemberService;
	
	@StreamListener(CreateMemberSink.CreateMember)
	public void members(Object payload) {
		System.out.println("Received: " + payload);
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		CreateMemberConfiguration member = (CreateMemberConfiguration) JSONObject.toBean(obj, CreateMemberConfiguration.class);
		createMemberService.save(member);
		
	}

}
