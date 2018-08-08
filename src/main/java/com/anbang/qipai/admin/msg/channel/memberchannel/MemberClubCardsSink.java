package com.anbang.qipai.admin.msg.channel.memberchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MemberClubCardsSink {

	String MEMBERCLUBCARDS = "memberClubCards";

	@Input
	SubscribableChannel memberClubCards();
}
