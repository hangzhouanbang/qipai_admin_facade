package com.anbang.qipai.admin.msg.channel.memberchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MemberOrdersSink {
	String orders = "orders";

	@Input
	SubscribableChannel orders();
}
