package com.anbang.qipai.admin.msg.receiver.agentsreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.AgentRewardsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.agents.AgentBackRewardRecordDbo;
import com.anbang.qipai.admin.plan.bean.agents.AgentRewardRecordDbo;
import com.anbang.qipai.admin.plan.bean.agents.AgentWithdrawRecordDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentRewardRecordDboService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;

@EnableBinding(AgentRewardsSink.class)
public class AgentRewardsMsgReceiver {

	@Autowired
	private AgentRewardRecordDboService agentRewardRecordDboService;

	@StreamListener(AgentRewardsSink.AGENTREWARDS)
	public void recordAgentRewardRecordDbo(CommonMO mo) {
		String msg = mo.getMsg();
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("give reward to agent".equals(msg)) {
			AgentRewardRecordDbo dbo = new AgentRewardRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAgentName((String) map.get("agentName"));
			dbo.setBossId((String) map.get("bossId"));
			dbo.setBossName((String) map.get("bossName"));
			dbo.setMemberId((String) map.get("memberId"));
			dbo.setMemberName((String) map.get("memberName"));
			dbo.setMemberHeadimgurl((String) map.get("memberHeadimgurl"));
			dbo.setPruduct((String) map.get("pruduct"));
			dbo.setTotalamount((double) map.get("totalamount"));
			dbo.setSeniorReward((double) map.get("seniorReward"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setAccountingAmount((double) map.get("accountingAmount"));
			dbo.setBalanceAfter((double) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			agentRewardRecordDboService.addAgentRewardRecordDbo(dbo);
		}
		if ("withdraw".equals(msg)) {
			AgentWithdrawRecordDbo dbo = new AgentWithdrawRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAgentId((String) map.get("agentId"));
			dbo.setAgentName((String) map.get("agentName"));
			dbo.setAgentHeadimgurl((String) map.get("agentHeadimgurl"));
			dbo.setAgentType((String) map.get("agentType"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setAccountingAmount((double) map.get("accountingAmount"));
			dbo.setBalanceAfter((double) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setState((String) map.get("state"));
			agentRewardRecordDboService.addAgentWithdrawRecordDbo(dbo);
		}
		if ("apply pass".equals(msg)) {
			String id = (String) map.get("id");
			String state = (String) map.get("state");
			agentRewardRecordDboService.agentRewardApplyPass(id, state);
		}
		if ("apply refuse".equals(msg)) {
			String id = (String) map.get("id");
			String desc = (String) map.get("desc");
			String state = (String) map.get("state");
			agentRewardRecordDboService.agentRewardApplyRefuse(id, state, desc);
		}
		if ("back reward to agent".equals(msg)) {
			AgentBackRewardRecordDbo dbo = new AgentBackRewardRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setAccountingAmount((double) map.get("accountingAmount"));
			dbo.setBalanceAfter((double) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			agentRewardRecordDboService.addAgentBackRewardRecordDbo(dbo);
		}
	}
}
