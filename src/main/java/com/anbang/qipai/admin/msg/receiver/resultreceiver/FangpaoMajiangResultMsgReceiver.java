package com.anbang.qipai.admin.msg.receiver.resultreceiver;


import com.anbang.qipai.admin.msg.channel.sink.resultsink.FangpaoMajiangResultSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.msjobj.MajiangHistoricalJuResultMO;
import com.anbang.qipai.admin.msg.msjobj.ResultEnum;
import com.anbang.qipai.admin.plan.service.reportservice.GameResultMsgService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(FangpaoMajiangResultSink.class)
public class FangpaoMajiangResultMsgReceiver {

    @Autowired
    private GameResultMsgService gameResultMsgService;

	private Gson gson = new Gson();

	@StreamListener(FangpaoMajiangResultSink.FANGPAOMAJIANGRESULT)
	public void recordMajiangHistoricalResult(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        //解析接受的消息
        MajiangHistoricalJuResultMO result=gson.fromJson(json,MajiangHistoricalJuResultMO.class);
        if (ResultEnum.FANGPAO_JU_RESULT.getMessage().equals(msg)) {
            gameResultMsgService.recordGameResult(result);
        }

	}
}
