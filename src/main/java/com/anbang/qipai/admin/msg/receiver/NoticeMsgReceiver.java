package com.anbang.qipai.admin.msg.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.NoticeSink;
import com.anbang.qipai.admin.plan.domain.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;

import net.sf.json.JSONObject;

/**接收系统公告返回消息类
 * @author 程佳 2018.6.1
 * 这里的类的注解是使一个或多个接口作为参数
 * **/
@EnableBinding(NoticeSink.class)
public class NoticeMsgReceiver {
	
	@Autowired
	private NoticeService noticeService;
	
	protected static Logger logger = LoggerFactory.getLogger(NoticeMsgReceiver.class);
	/**接收信息的方法
	 * @param payload 接收的json数据
	 * 这里的方法的注解做消息监听
	 * **/
	@StreamListener(NoticeSink.game)
	public void notice(Object payload){
		logger.info("--------"+payload);
		System.out.println("Received: " + payload);
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		Notice notice = (Notice) JSONObject.toBean(obj, Notice.class);
		noticeService.addNotice(notice);
	}
	

}
