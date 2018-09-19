package com.anbang.qipai.admin.msg.receiver.memberreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.MemberScoresSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberScoreService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;
import com.google.gson.Gson;

@EnableBinding(MemberScoresSink.class)
public class MemberScoresMsgReceiver {
	@Autowired
	private MemberScoreService memberScoreService;
	
	@Autowired
	private MemberDboService memberDboService;

	private Gson gson = new Gson();

	@StreamListener(MemberScoresSink.MEMBERSCORES)
	public void recordMemberScoreRecordDbo(CommonMO mo) {
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(mo.getMsg())) {
			MemberScoreRecordDbo dbo = new MemberScoreRecordDbo();
			dbo.setId((String) map.get("id"));
			dbo.setAccountId((String) map.get("accountId"));
			dbo.setMemberId((String) map.get("memberId"));
			dbo.setAccountingNo(Long.valueOf((int) map.get("accountingNo")));
			dbo.setBalanceAfter((int) map.get("balanceAfter"));
			AccountingSummary summary = new TextAccountingSummary(
					(String) ((Map<String, Object>) map.get("summary")).get("text"));
			dbo.setSummary(summary);
			dbo.setAccountingTime((long) map.get("accountingTime"));
			dbo.setAccountingAmount((int) map.get("accountingAmount"));
			memberScoreService.addScoreRecord(dbo);
			MemberDbo memberDbo = new MemberDbo();
			memberDbo.setScore((int) map.get("balanceAfter"));
			memberDboService.updateMemberGold(memberDbo);
		}
	}
}
