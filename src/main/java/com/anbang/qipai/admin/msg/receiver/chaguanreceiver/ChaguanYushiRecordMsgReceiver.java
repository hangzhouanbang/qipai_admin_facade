package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanYushiRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanYushiService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;
import com.google.gson.Gson;

@EnableBinding(ChaguanYushiRecordSink.class)
public class ChaguanYushiRecordMsgReceiver {
	@Autowired
	private ChaguanYushiService chaguanYushiService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanYushiRecordSink.CHAGUANYUSHIRECORD)
	public void record(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(msg)) {
			ChaguanYushiRecordDbo dbo = new ChaguanYushiRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfter((int) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			chaguanYushiService.recordChaguanYushiRecordDbo(dbo);
		}
	}
}
