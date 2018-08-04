package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.NoticeSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.notice.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;
import com.google.gson.Gson;

/**
 * 接收系统公告返回消息类
 * 
 * @author 程佳 2018.6.1 这里的类的注解是使一个或多个接口作为参数
 **/
@EnableBinding(NoticeSink.class)
public class NoticeMsgReceiver {

	@Autowired
	private NoticeService noticeService;

	private Gson gson = new Gson();

	/**
	 * 接收信息的方法
	 * 
	 * @param payload
	 *            接收的json数据 这里的方法的注解做消息监听
	 **/
	@StreamListener(NoticeSink.notice)
	public void notice(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		Notice notice = gson.fromJson(json, Notice.class);
		noticeService.addNotice(notice);
	}

}
