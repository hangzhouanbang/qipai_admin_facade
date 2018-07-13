package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MembersSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.anbang.qipai.admin.plan.service.MemberDboService;
import com.google.gson.Gson;

@EnableBinding(MembersSink.class)
public class MembersMsgReceiver {

	@Autowired
	private MemberDboService memberService;

	private Gson gson = new Gson();

	@StreamListener(MembersSink.MEMBERS)
	public void recordMember(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		MemberDbo member = gson.fromJson(json, MemberDbo.class);
		if ("newMember".equals(msg)) {
			memberService.addMember(member);
		}
		if ("update member phone".equals(msg)) {
			memberService.updateMemberPhone(member);
		}
		if ("reset member vip".equals(msg)) {
			memberService.resetMemberVip(member);
		}
		if ("update member vip".equals(msg)) {
			memberService.updateMemberVip(member);
		}
	}

}
