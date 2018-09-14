package com.anbang.qipai.admin.msg.channel.gamechannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GameServerManagerSource {

    @Output
    MessageChannel gameServerManager();

}
