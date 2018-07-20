package com.anbang.qipai.admin.msg.receiver.memberreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.memberchannel.MemberOrdersSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.members.MemberOrder;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.google.gson.Gson;

@EnableBinding(MemberOrdersSink.class)
public class MemberOrdersMsgReceiver {
	@Autowired
	private MemberOrderService orderService;

	private Gson gson = new Gson();

	@StreamListener(MemberOrdersSink.orders)
	public void recordOrder(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		MemberOrder order = gson.fromJson(json, MemberOrder.class);
		if ("newOrder".equals(mo.getMsg())) {
			orderService.addOrder(order);
		}
		if ("trade over".equals(mo.getMsg())) {
			orderService.updateOrderStatusAndDeliveTime(order);
		}
	}
}
