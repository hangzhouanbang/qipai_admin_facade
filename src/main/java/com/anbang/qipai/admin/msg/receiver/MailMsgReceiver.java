package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MailSink;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.MailService;

import net.sf.json.JSONObject;
/**接收邮件返回消息类
 * @author 程佳 2018.6.6
 * 这里的类的注解是使一个或多个接口作为参数
 * **/
@EnableBinding(MailSink.class)
public class MailMsgReceiver {
	
	@Autowired
	private MailService mailService;
	
	/**接收信息的方法
	 * @param payload 接收的json数据
	 * 这里的方法的注解做消息监听
	 * **/
	@StreamListener(MailSink.mail)
	public void mail(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		SystemMail mail = (SystemMail) JSONObject.toBean(obj, SystemMail.class);
		mailService.addmail(mail);
	}

}
