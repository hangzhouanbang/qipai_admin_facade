package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AgentClubCardsSink {
	String AGENTCLUBCARDS = "agentClubCards";

	@Input
	SubscribableChannel agentClubCards();
}
