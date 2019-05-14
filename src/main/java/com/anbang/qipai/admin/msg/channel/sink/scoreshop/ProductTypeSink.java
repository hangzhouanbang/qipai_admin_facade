package com.anbang.qipai.admin.msg.channel.sink.scoreshop;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProductTypeSink {
    String PRODUCT_TYPE = "productType";

    @Input
    SubscribableChannel productType();
}
