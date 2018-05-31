package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface MembersSink {

	String MEMBERS = "members";

	@Input
	MessageChannel members();

}
