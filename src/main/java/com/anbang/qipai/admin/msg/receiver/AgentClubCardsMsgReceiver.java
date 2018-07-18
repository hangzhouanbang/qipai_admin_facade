package com.anbang.qipai.admin.msg.receiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.AgentScoresSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agent.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentClubCardRecordDboService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;
import com.google.gson.Gson;

@EnableBinding(AgentScoresSink.class)
public class AgentClubCardsMsgReceiver {
	@Autowired
	private AgentClubCardRecordDboService agentClubCardRecordDboService;

	private Gson gson = new Gson();

	@StreamListener(AgentScoresSink.AGENTSCORES)
	public void recordAgentClubCardRecordDbo(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(msg)) {
			AgentClubCardRecordDbo dbo = new AgentClubCardRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setProduct((String) map.get("product"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfter((int) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			agentClubCardRecordDboService.addAgentClubCardRecordDbo(dbo);
		}
	}
}
