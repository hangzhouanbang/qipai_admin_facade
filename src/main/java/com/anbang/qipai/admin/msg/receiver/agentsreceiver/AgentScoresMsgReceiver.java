package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.agentschannel.AgentScoresSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.agents.AgentScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentScoreRecordDboService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;

@EnableBinding(AgentScoresSink.class)
public class AgentScoresMsgReceiver {
	@Autowired
	private AgentScoreRecordDboService agentScoreRecordDboService;

	@StreamListener(AgentScoresSink.AGENTSCORES)
	public void recordAgentScoreRecordDbo(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(msg)) {
			AgentScoreRecordDbo dbo = new AgentScoreRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAgent((String) map.get("agent"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfter((int) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			agentScoreRecordDboService.addAgentScoreRecordDbo(dbo);
		}
	}
}
