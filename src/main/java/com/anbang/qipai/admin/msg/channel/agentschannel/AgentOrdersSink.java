package com.anbang.qipai.admin.msg.channel.agentschannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AgentOrdersSink {
	String agentOrders = "agentOrders";

	@Input
	SubscribableChannel agentOrders();
}
