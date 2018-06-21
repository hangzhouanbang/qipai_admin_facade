package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrdersSink {
	String orders = "orders";

	@Input
	SubscribableChannel orders();
}
