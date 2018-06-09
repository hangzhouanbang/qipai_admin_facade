package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.NewMemberSink;
import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.MemberService;

import net.sf.json.JSONObject;

@EnableBinding(NewMemberSink.class)
public class AddMemberReceiver {

	@Autowired
	private MemberService memberService;

	@StreamListener(NewMemberSink.member)
	public void addMember(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		Member member = (Member) JSONObject.toBean(obj, Member.class);
		memberService.addMember(member);
	}
}
