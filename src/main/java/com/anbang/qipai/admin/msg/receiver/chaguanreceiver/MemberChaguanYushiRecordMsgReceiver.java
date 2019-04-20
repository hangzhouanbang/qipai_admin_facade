package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.MemberChaguanYushiRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.MemberChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.MemberChaguanYushiService;
import com.google.gson.Gson;

@EnableBinding(MemberChaguanYushiRecordSink.class)
public class MemberChaguanYushiRecordMsgReceiver {
	@Autowired
	private MemberChaguanYushiService memberChaguanYushiService;

	private Gson gson = new Gson();

	@StreamListener(MemberChaguanYushiRecordSink.MEMBERCHAGUANYUSHIRECORD)
	public void record(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		MemberChaguanYushiRecordDbo dbo = gson.fromJson(json, MemberChaguanYushiRecordDbo.class);
		if ("accounting".equals(msg)) {
			memberChaguanYushiService.recordMemberChaguanYushiRecordDbo(dbo);
		}
	}
}
