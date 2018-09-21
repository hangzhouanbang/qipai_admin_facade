package com.anbang.qipai.admin.msg.receiver.gamereceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.SignInPrizeLogSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeExchangeLog;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeLog;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeExchangeLogService;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeLogService;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeService;
import com.google.gson.Gson;

/**
 * 接受中奖记录消息
*@author   created by hanzhuofan  2018.09.18
*/
@EnableBinding(SignInPrizeLogSink.class)
public class SignInPrizeLogMsgReceiver {
	
	@Autowired
	private SignInPrizeService signInPrizeService;
	
	@Autowired
	private SignInPrizeLogService signInPrizeLogService;
	
	@Autowired
	private SignInPrizeExchangeLogService signInPrizeExchangeLogService;
	
	private Gson gson = new Gson();
	
	@StreamListener(SignInPrizeLogSink.channel)
	public void addSignInPrizeLog(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if (SignInPrizeLogSink.addSignInPrizeLog.equals(msg)) {
			SignInPrizeLog signInPrizeLog = gson.fromJson(json, SignInPrizeLog.class);
			signInPrizeLogService.addSignInPrizeLog(signInPrizeLog);
			String id = signInPrizeLog.getSignInPrizeId();
			if (signInPrizeService.querySignInPrizeById(id).getType().equals("实物")) {
				SignInPrizeExchangeLog signInPrizeExchangeLog =new SignInPrizeExchangeLog();
				signInPrizeExchangeLog.setSignInPrizeLog(signInPrizeLog);
				signInPrizeExchangeLogService.addSignInPrizeExchangeLog(signInPrizeExchangeLog);
			}
			if (signInPrizeService.querySignInPrizeById(id).getState().equals("0")) {
				signInPrizeService.decreaseStoreById(id);
			}
		}
	}
	
	@StreamListener(SignInPrizeLogSink.channel)
	public void addSignInPrizeExchangeLog(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if (SignInPrizeLogSink.addSignInPrizeExchangeLog.equals(msg)) {
			SignInPrizeExchangeLog signInPrizeExchangeLog = gson.fromJson(json, SignInPrizeExchangeLog.class);
			signInPrizeExchangeLogService.updateSignInPrizeExchangeLog(signInPrizeExchangeLog);
		}
	}
}
