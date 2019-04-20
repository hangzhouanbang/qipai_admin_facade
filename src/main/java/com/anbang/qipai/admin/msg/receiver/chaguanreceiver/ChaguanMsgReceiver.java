package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanService;
import com.google.gson.Gson;

@EnableBinding(ChaguanSink.class)
public class ChaguanMsgReceiver {
	@Autowired
	private ChaguanService chaguanService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanSink.CHAGUAN)
	public void chaguan(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanDbo dbo = gson.fromJson(json, ChaguanDbo.class);
		if ("new chaguan".equals(msg)) {
			chaguanService.saveChaguanDbo(dbo);
		}
		if ("update chaguan".equals(msg)) {
			chaguanService.updateChaguanDbo(dbo);
		}
	}
}
