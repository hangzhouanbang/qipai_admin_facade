package com.anbang.qipai.admin.msg.channel.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
*@author   created by hanzhuofan  2018.09.18
*/
public interface SignInPrizeLogSink {

	String channel = "signInPrizeLog";
	String addSignInPrizeLog = "addSignInPrizeLog";
	String addSignInPrizeExchangeLog = "addSignInPrizeExchangeLog";
	
	@Input
	SubscribableChannel signInPrizeLog();
}
