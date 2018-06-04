package com.anbang.qipai.admin.msg.receiver;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MembersSink;

@EnableBinding(MembersSink.class)
public class MembersMsgReceiver {

	@StreamListener(MembersSink.MEMBERS)
	public void members(Object payload) {
		System.out.println("Received: " + payload);
	}

}
