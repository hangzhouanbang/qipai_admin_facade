package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.CreateMemberSink;
import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
import com.anbang.qipai.admin.plan.service.CreateMemberService;

import net.sf.json.JSONObject;

/**接收设置金币返回消息类
 * @author 程佳 2018.6.1
 * 这里的类的注解是使一个或多个接口作为参数
 * **/
@EnableBinding(CreateMemberSink.class)
public class CreateMemberMsgReceiver {
	
	@Autowired
	private CreateMemberService createMemberService;
	
	/**接收信息的方法
	 * @param payload 接收的json数据
	 * 这里的方法的注解做消息监听
	 * **/
	@StreamListener(CreateMemberSink.CreateMember)
	public void members(Object payload) {
		System.out.println("Received: " + payload);
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		CreateMemberConfiguration member = (CreateMemberConfiguration) JSONObject.toBean(obj, CreateMemberConfiguration.class);
		createMemberService.save(member);
		
	}

}
