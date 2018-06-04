package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
import com.anbang.qipai.admin.plan.service.MemberService;
import com.anbang.qipai.admin.remote.MemberQipaClients;



/**
 * 设置金币Controller
 * @author 程佳 2018.5.31
 * **/
@Controller
public class CreateMemberCtrl {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberQipaClients qipaMemberClient;

	/**
	 * @param goldForNewMember  设置赠送金币的数量
	 * @return 操作结果
	 * **/
	@RequestMapping("/addMember")
	@ResponseBody
	public String addMember(Integer goldForNewMember) {
		if(goldForNewMember == null) {
			return "file";
		}
		CreateMemberConfiguration m = memberService.save(goldForNewMember);
		if(m != null) {
			qipaMemberClient.qipaAdmin(goldForNewMember);
			return "success";
		}
		return "file";
	}
	
}
