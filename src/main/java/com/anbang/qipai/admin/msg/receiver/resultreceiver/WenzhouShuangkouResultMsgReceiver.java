package com.anbang.qipai.admin.msg.receiver.resultreceiver;

import com.anbang.qipai.admin.msg.channel.sink.resultsink.WenzhouShuangkouResultSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.msjobj.MajiangHistoricalJuResultMO;
import com.anbang.qipai.admin.msg.msjobj.ResultEnum;
import com.anbang.qipai.admin.plan.service.reportservice.GameResultMsgService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(WenzhouShuangkouResultSink.class)
public class WenzhouShuangkouResultMsgReceiver {

	private Gson gson = new Gson();

	@StreamListener(WenzhouShuangkouResultSink.WENZHOUSHUANGKOURESULT)
	public void recordMajiangHistoricalResult(CommonMO mo) {

	}
}
