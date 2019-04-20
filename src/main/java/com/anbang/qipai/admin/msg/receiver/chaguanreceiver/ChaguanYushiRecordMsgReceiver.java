package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanYushiRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanYushiService;
import com.google.gson.Gson;

@EnableBinding(ChaguanYushiRecordSink.class)
public class ChaguanYushiRecordMsgReceiver {
	@Autowired
	private ChaguanYushiService chaguanYushiService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanYushiRecordSink.CHAGUANYUSHIRECORD)
	public void record(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanYushiRecordDbo dbo = gson.fromJson(json, ChaguanYushiRecordDbo.class);
		if ("accounting".equals(msg)) {
			chaguanYushiService.recordChaguanYushiRecordDbo(dbo);
		}
	}
}
