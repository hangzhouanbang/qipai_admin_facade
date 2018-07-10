package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.OrdersSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.plan.service.OrderService;
import com.google.gson.Gson;

@EnableBinding(OrdersSink.class)
public class OrdersMsgReceiver {
	@Autowired
	private OrderService orderService;

	private Gson gson = new Gson();

	@StreamListener(OrdersSink.orders)
	public void recordOrder(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		Order order = gson.fromJson(json, Order.class);
		if ("newOrder".equals(mo.getMsg())) {
			orderService.addOrder(order);
		}
		if ("trade over".equals(mo.getMsg())) {
			orderService.updateOrderStatusAndDeliveTime(order);
		}
	}
}
