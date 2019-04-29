package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.MemberChaguanYushiRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.MemberChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.service.chaguanservice.MemberChaguanYushiService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;
import com.google.gson.Gson;

@EnableBinding(MemberChaguanYushiRecordSink.class)
public class MemberChaguanYushiRecordMsgReceiver {
	@Autowired
	private MemberChaguanYushiService memberChaguanYushiService;

	private Gson gson = new Gson();

	@StreamListener(MemberChaguanYushiRecordSink.MEMBERCHAGUANYUSHIRECORD)
	public void record(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(msg)) {
			MemberChaguanYushiRecordDbo dbo = new MemberChaguanYushiRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setMemberId((String) map.get("memberId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfter((int) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			memberChaguanYushiService.recordMemberChaguanYushiRecordDbo(dbo);
		}
	}
}
