package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanGameTableSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanGameTable;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanService;
import com.google.gson.Gson;

@EnableBinding(ChaguanGameTableSink.class)
public class ChaguanGameTableMsgReceiver {

	@Autowired
	private ChaguanService chaguanService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanGameTableSink.CHAGUANGAMETABLE)
	public void chaguanGameTable(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanGameTable table = gson.fromJson(json, ChaguanGameTable.class);
		if ("new gametable".equals(msg)) {
			chaguanService.saveChaguanGameTable(table);
		}
		if ("update gametable".equals(msg)) {
			chaguanService.updateChaguanGameTable(table);
		}
	}
}
