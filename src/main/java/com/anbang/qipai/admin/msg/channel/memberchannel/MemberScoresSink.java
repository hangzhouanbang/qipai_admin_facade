package com.anbang.qipai.admin.msg.channel.memberchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MemberScoresSink {
	String MEMBERSCORES = "memberScores";

	@Input
	SubscribableChannel memberScores();
}