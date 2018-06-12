package com.anbang.qipai.admin.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
/**接收金币返回消息
 * @author 程佳 2018.6.1
 * **/
public interface MemberShipRightsSink {
	
	String membershiprights = "membershiprights";
	
	@Input
	MessageChannel membershiprights();
	
}
