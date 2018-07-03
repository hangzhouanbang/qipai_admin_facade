package com.anbang.qipai.admin.msg.receiver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MailSink;
import com.anbang.qipai.admin.plan.domain.mail.MailState;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.MailService;

import net.sf.json.JSONArray;
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
		String msg = (String) json.get("msg");
		//添加返回邮件信息
		if(msg.equals("newMail")) {
			JSONObject obj = (JSONObject) json.get("data");
			SystemMail mail = (SystemMail) JSONObject.toBean(obj, SystemMail.class);
			mailService.addmail(mail);
		}
		//添加返回邮件状态
		if(msg.equals("newMailState")) {
			JSONArray array = json.getJSONArray("data");
			JSONArray jsonarray = JSONArray.fromObject(array);
			@SuppressWarnings("unchecked")
			List<MailState> lists = (List<MailState>) JSONArray.toCollection(jsonarray,MailState.class);
			mailService.addMailById(lists);
		}
		
		//修改返回邮件状态
		if(msg.equals("updateMailState")) {
			JSONObject obj = (JSONObject) json.get("data");
			MailState mailState = (MailState) JSONObject.toBean(obj, MailState.class);
			mailService.updateMailState(mailState);
		}
		
		//所有的设为已读
		if(msg.equals("updateMailStateAll")) {
			String memberId = (String) json.get("data");
			mailService.findallmembermail(memberId);
		}
		
		//删除所有已读
		if(msg.equals("deleteMailStateAll")) {
			String memberId = (String) json.get("data");
			mailService.deleteallmail(memberId);
		}
	}

}
