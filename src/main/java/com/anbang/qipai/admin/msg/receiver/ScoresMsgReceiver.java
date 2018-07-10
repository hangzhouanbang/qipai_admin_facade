package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.ScoresSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.record.MemberScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.MemberScoreService;
import com.google.gson.Gson;

@EnableBinding(ScoresSink.class)
public class ScoresMsgReceiver {
	@Autowired
	private MemberScoreService memberScoreService;

	private Gson gson = new Gson();

	@StreamListener(ScoresSink.scores)
	public void recordMemberScoreRecordDbo(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		MemberScoreRecordDbo dbo = gson.fromJson(json, MemberScoreRecordDbo.class);
		if ("accounting".equals(mo.getMsg())) {
			memberScoreService.addScoreRecord(dbo);
		}
	}
}
