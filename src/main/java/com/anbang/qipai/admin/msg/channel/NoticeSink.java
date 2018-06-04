package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface NoticeSink {

	String game = "game";
	
	@Input
	MessageChannel game();
}
