package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MembersSink;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.anbang.qipai.admin.plan.service.MemberService;

import net.sf.json.JSONObject;

@EnableBinding(MembersSink.class)
public class MembersMsgReceiver {

	@Autowired
	private MemberService memberService;

	@StreamListener(MembersSink.MEMBERS)
	public void addMember(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject objmsg = (JSONObject) json.get("msg");
		String msg = (String) JSONObject.toBean(objmsg);
		if ("newMember".equals(msg)) {
			JSONObject obj = (JSONObject) json.get("data");
			MemberDbo member = (MemberDbo) JSONObject.toBean(obj);
			memberService.addMember(member);
		}
	}

}
