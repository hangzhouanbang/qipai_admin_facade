package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.OrdersSink;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.plan.service.OrderService;

import net.sf.json.JSONObject;

@EnableBinding(OrdersSink.class)
public class OrdersMsgReceiver {
	@Autowired
	private OrderService orderService;

	@StreamListener(OrdersSink.orders)
	public void addOrder(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		String msg = (String) json.get("msg");
		if ("newOrder".equals(msg)) {
			JSONObject obj = (JSONObject) json.get("data");
			Order order = (Order) JSONObject.toBean(obj, Order.class);
			orderService.addOrder(order);
		}
		if ("updateOrder".equals(msg)) {
			JSONObject obj = (JSONObject) json.get("data");
			Order order = (Order) JSONObject.toBean(obj, Order.class);
			orderService.updateOrder(order);
		}
	}
}
