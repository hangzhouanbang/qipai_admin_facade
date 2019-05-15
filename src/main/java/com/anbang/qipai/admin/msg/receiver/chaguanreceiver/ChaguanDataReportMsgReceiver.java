package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanDataReportSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanGameDataReport;
import com.anbang.qipai.admin.plan.service.GameReportService;
import com.google.gson.Gson;

@EnableBinding(ChaguanDataReportSink.class)
public class ChaguanDataReportMsgReceiver {

	@Autowired
	private GameReportService gameReportService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanDataReportSink.CHAGUANDATAREPORT)
	public void chaguanDataReport(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanGameDataReport report = gson.fromJson(json, ChaguanGameDataReport.class);
		if ("record chaguanDataReport".equals(msg)) {
			gameReportService.addChaguanGameDataReport(report);
		}
	}
}
