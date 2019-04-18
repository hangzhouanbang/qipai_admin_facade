package com.anbang.qipai.admin.msg.receiver.xiuxianchangreceiver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.MemberXiuxianchangGoldsSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.google.gson.Gson;

@EnableBinding(MemberXiuxianchangGoldsSink.class)
public class MemberXiuxianchangGoldsMsgReceiver {

	@Autowired
	private MemberDboService memberService;

	private Gson gson = new Gson();

	@StreamListener(MemberXiuxianchangGoldsSink.MEMBERXIUXIANCHANGGOLDS)
	public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
		Map<String, Object> map = (Map<String, Object>) mo.getData();
		if ("accounting".equals(mo.getMsg())) {
			String memberId = (String) map.get("memberId");
			memberService.updateMemberXiuxianchangGold(memberId, (int) map.get("balanceAfter"));
		}
	}
}
