package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.anbang.qipai.admin.msg.channel.NoticeSink;
import com.anbang.qipai.admin.plan.domain.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;

import net.sf.json.JSONObject;


@EnableBinding(NoticeSink.class)
public class NoticeMsgReceiver {
	
	@Autowired
	private NoticeService noticeService;
	
	@StreamListener(NoticeSink.game)
	public void notice(Object payload){
		System.out.println("Received: " + payload);
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		Notice notice = (Notice) JSONObject.toBean(obj, Notice.class);
		noticeService.addNotice(notice);
	}
	

}
