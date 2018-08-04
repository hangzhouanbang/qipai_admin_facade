package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.agentschannel.AgentClubCardsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentClubCardRecordDboService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;

@EnableBinding(AgentClubCardsSink.class)
public class AgentClubCardsMsgReceiver {
	@Autowired
	private AgentClubCardRecordDboService agentClubCardRecordDboService;

	@StreamListener(AgentClubCardsSink.AGENTCLUBCARDS)
	public void recordAgentClubCardRecordDbo(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(msg)) {
			AgentClubCardRecordDbo dbo = new AgentClubCardRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAgent((String) map.get("agent"));
			dbo.setProduct((String) map.get("product"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfterZhou((int) map.get("balanceAfterZhou"));
			dbo.setBalanceAfterYue((int) map.get("balanceAfterYue"));
			dbo.setBalanceAfterJi((int) map.get("balanceAfterJi"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			dbo.setCost((double) map.get("cost"));
			dbo.setReceiverId((String) map.get("receiverId"));
			dbo.setReceiver((String) map.get("receiver"));
			agentClubCardRecordDboService.addAgentClubCardRecordDbo(dbo);
		}
	}
}
