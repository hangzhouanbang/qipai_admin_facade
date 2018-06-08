package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface MailSink {
	
	String mail = "mail";

	@Input
	MessageChannel mail();
}
