package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GoldsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.record.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.service.MemberGoldService;
import com.google.gson.Gson;

@EnableBinding(GoldsSink.class)
public class GoldsMsgReceiver {

	@Autowired
	private MemberGoldService memberGoldService;

	private Gson gson = new Gson();

	@StreamListener(GoldsSink.golds)
	public void recordMemberGoldRecordDbo(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		MemberGoldRecordDbo dbo = gson.fromJson(json, MemberGoldRecordDbo.class);
		if ("accounting".equals(mo.getMsg())) {
			memberGoldService.addGoldRecord(dbo);
		}
	}
}
