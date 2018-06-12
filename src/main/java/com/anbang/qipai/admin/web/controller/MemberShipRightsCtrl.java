package com.anbang.qipai.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.membershiprights.CommonUser;
import com.anbang.qipai.admin.plan.domain.membershiprights.VipUser;
import com.anbang.qipai.admin.remote.service.QipaiMembersService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

import net.sf.json.JSONObject;

/**
 * 会员权益Controller
 * 
 * @author 程佳 2018.5.31
 **/
@RestController
@RequestMapping("rights")
public class MemberShipRightsCtrl {

	@Autowired
	private QipaiMembersService qipaMemberClient;
	
	private static Logger logger = LoggerFactory.getLogger(MemberShipRightsCtrl.class);

	/**
	 * @param 普通会员所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/commonuser")
	@ResponseBody
	public String commonuser(CommonUser commonuser) {
		JSONObject json = JSONObject.fromObject(commonuser);
		String str = json.toString();
		logger.info("aaa"+str);
		CommonRemoteVO co = qipaMemberClient.commonuser(str);
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}
	}
	
	/**
	 * @param vip会员所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/vipuser")
	@ResponseBody
	public String vipuser(VipUser vipuser) {
		JSONObject json = JSONObject.fromObject(vipuser);
		String str = json.toString();
		logger.info("bbb"+str);
		CommonRemoteVO co = qipaMemberClient.vipuser(str);
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}
	}
	
}
