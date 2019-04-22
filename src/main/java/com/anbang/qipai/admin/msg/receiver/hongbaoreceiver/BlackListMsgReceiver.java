package com.anbang.qipai.admin.msg.receiver.hongbaoreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.BlackListSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.hongbao.BlackList;
import com.anbang.qipai.admin.plan.service.tasksservice.WhiteListService;
import com.google.gson.Gson;

@EnableBinding(BlackListSink.class)
public class BlackListMsgReceiver {
	@Autowired
	private WhiteListService whiteListService;

	private Gson gson = new Gson();

	@StreamListener(BlackListSink.BLACKLIST)
	public void memberClubCard(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("add blackList".equals(msg)) {
			BlackList blackList = gson.fromJson(json, BlackList.class);
			whiteListService.saveBlackList(blackList);
		}
		if ("remove blackList".equals(msg)) {
			String[] ids = gson.fromJson(json, String[].class);
			whiteListService.removeBlackList(ids);
		}
	}
}
