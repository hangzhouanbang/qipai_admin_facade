package com.anbang.qipai.admin.msg.receiver.memberreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.MemberGoldsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberGoldService;
import com.dml.accounting.AccountingSummary;
import com.dml.accounting.TextAccountingSummary;
import com.google.gson.Gson;

@EnableBinding(MemberGoldsSink.class)
public class MemberGoldsMsgReceiver {

	@Autowired
	private MemberGoldService memberGoldService;

	@Autowired
	private MemberDboService memberService;

	private Gson gson = new Gson();

	@StreamListener(MemberGoldsSink.MEMBERGOLDS)
	public void recordMemberGoldRecordDbo(CommonMO mo) {
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(mo.getMsg())) {
			long no = Long.valueOf((int) map.get("accountingNo"));
			String memberId = (String) map.get("memberId");
			MemberGoldRecordDbo recentDbo = memberGoldService.findRecentlyGoldRecordByMemberId(memberId);
			if (recentDbo == null || no > recentDbo.getAccountingNo()) {
				MemberGoldRecordDbo dbo = new MemberGoldRecordDbo();
				dbo.setId((String) map.get("id"));
				dbo.setAccountId((String) map.get("accountId"));
				dbo.setMemberId(memberId);
				dbo.setAccountingNo(no);
				dbo.setBalanceAfter((int) map.get("balanceAfter"));
				AccountingSummary summary = new TextAccountingSummary(
						(String) ((Map<String, Object>) map.get("summary")).get("text"));
				dbo.setSummary(summary);
				dbo.setAccountingTime((long) map.get("accountingTime"));
				dbo.setAccountingAmount((int) map.get("accountingAmount"));
				memberGoldService.addGoldRecord(dbo);
				memberService.updateMemberGold(memberId, (int) map.get("balanceAfter"));
			}
		}
	}
}
