package com.anbang.qipai.admin.msg.receiver.memberreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.MembersSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
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
		if ("update member vip".equals(msg)) {
			memberService.updateMemberVip(member);
		}
		if ("memberOrder delive".equals(msg)) {
			memberService.memberOrderDelive(member);
		}
		if ("recharge vip".equals(msg)) {
			memberService.rechargeVip(member);
		}
		if ("update member realUser".equals(msg)) {
			memberService.updateMemberRealUser(member);
		}
	}

}
