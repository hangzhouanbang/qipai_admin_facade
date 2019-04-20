package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanMemberSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanMemberDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanService;
import com.google.gson.Gson;

@EnableBinding(ChaguanMemberSink.class)
public class ChaguanMemberMsgReceiver {
	@Autowired
	private ChaguanService chaguanService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanMemberSink.CHAGUANMEMBER)
	public void chaguanMember(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanMemberDbo dbo = gson.fromJson(json, ChaguanMemberDbo.class);
		if ("new chaguan member".equals(msg)) {
			chaguanService.saveChaguanMemberDbo(dbo);
		}
		if ("remove chaguan member".equals(msg)) {
			chaguanService.removeChaguanMemberDbo(dbo);
		}
		if ("set chaguan member".equals(msg)) {
			chaguanService.setChaguanMemberDbo(dbo);
		}
	}
}
