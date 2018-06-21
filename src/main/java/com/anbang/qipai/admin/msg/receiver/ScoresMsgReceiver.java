package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.ScoresSink;
import com.anbang.qipai.admin.plan.domain.record.MemberScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.MemberScoreService;

import net.sf.json.JSONObject;

@EnableBinding(ScoresSink.class)
public class ScoresMsgReceiver {
	@Autowired
	private MemberScoreService memberScoreService;

	@StreamListener(ScoresSink.scores)
	public void addMemberScoreRecordDbo(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		MemberScoreRecordDbo dbo = (MemberScoreRecordDbo) JSONObject.toBean(obj, MemberScoreRecordDbo.class);
		memberScoreService.addScoreRecord(dbo);
	}
}
