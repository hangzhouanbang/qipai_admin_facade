package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GoldsSink {
	String golds = "golds";

	@Input
	SubscribableChannel golds();
}
