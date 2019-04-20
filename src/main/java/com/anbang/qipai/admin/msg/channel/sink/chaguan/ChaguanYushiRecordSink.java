package com.anbang.qipai.admin.msg.channel.sink.chaguan;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ChaguanYushiRecordSink {
	String CHAGUANYUSHIRECORD = "chaguanYushiRecord";

	@Input
	SubscribableChannel chaguanYushiRecord();
}
