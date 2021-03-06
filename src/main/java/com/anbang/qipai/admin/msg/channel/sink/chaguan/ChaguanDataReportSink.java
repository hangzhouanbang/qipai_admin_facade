package com.anbang.qipai.admin.msg.channel.sink.chaguan;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ChaguanDataReportSink {
	String CHAGUANDATAREPORT = "chaguanDataReport";

	@Input
	SubscribableChannel chaguanDataReport();
}
