package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanApplySink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanApply;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanApplyService;
import com.google.gson.Gson;

@EnableBinding(ChaguanApplySink.class)
public class ChaguanApplyMsgReceiver {
	@Autowired
	private ChaguanApplyService chaguanApplyService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanApplySink.CHAGUANAPPLY)
	public void chaguanApply(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanApply apply = gson.fromJson(json, ChaguanApply.class);
		if ("new apply".equals(msg)) {
			chaguanApplyService.saveChaguanApply(apply);
		}
		if ("pass apply".equals(msg)) {
			chaguanApplyService.chaguanApplyPass(apply);
		}
		if ("refuse apply".equals(msg)) {
			chaguanApplyService.chaguanApplyRefuse(apply);
		}
	}
}
