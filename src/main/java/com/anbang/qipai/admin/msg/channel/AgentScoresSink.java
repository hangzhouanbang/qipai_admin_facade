package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AgentScoresSink {
	String AGENTSCORES = "agentScores";

	@Input
	SubscribableChannel agentScores();
}
