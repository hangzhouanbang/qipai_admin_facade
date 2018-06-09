package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.remote.MemberQipaClients;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 设置金币Controller
 * 
 * @author 程佳 2018.5.31
 **/
@RestController
@RequestMapping("cmctrl")
public class CreateMemberCtrl {

	@Autowired
	private MemberQipaClients qipaMemberClient;

	/**
	 * @param goldForNewMember
	 *            设置赠送金币的数量
	 * @return 操作结果
	 **/
	@RequestMapping("/addmember")
	@ResponseBody
	public String addMember(Integer goldForNewMember) {
		CommonRemoteVO co = qipaMemberClient.qipaAdmin(goldForNewMember);
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}
	}

}
