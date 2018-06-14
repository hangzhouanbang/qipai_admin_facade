package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GoldsSink;
import com.anbang.qipai.admin.plan.domain.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.service.MemberGoldService;

import net.sf.json.JSONObject;

@EnableBinding(GoldsSink.class)
public class GoldsMsgReceiver {

	@Autowired
	private MemberGoldService memberGoldService;

	@StreamListener(GoldsSink.golds)
	public void addMemberGoldRecordDbo(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		MemberGoldRecordDbo dbo = (MemberGoldRecordDbo) JSONObject.toBean(obj, MemberGoldRecordDbo.class);
		memberGoldService.addGoldRecord(dbo);
	}
}
