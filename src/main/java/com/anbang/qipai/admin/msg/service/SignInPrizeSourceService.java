package com.anbang.qipai.admin.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import com.anbang.qipai.admin.msg.channel.source.SignInPrizeSource;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrize;
import com.anbang.qipai.admin.plan.dao.signindao.SignInPrizeDao;

/**
*@author   created by hanzhuofan  2018.09.18
*/
@EnableBinding(SignInPrizeSource.class)
public class SignInPrizeSourceService {

	@Autowired
	private SignInPrizeSource signInPrizeSource;
	
	@Autowired
	private SignInPrizeDao signInPrizeDao;

	public void releaseSignInPrize() {
		List<SignInPrize> signInPrizes = signInPrizeDao.querySignInPrize();
		CommonMO commonMO = new CommonMO();
        commonMO.setMsg(SignInPrizeSource.releaseSignInPrize);
        commonMO.setData(signInPrizes);
		signInPrizeSource.signInPrize().send(MessageBuilder.withPayload(commonMO).build());
	}
}
