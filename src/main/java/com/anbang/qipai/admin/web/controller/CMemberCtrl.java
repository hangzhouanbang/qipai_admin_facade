package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.CMemberService;

public class CMemberCtrl {

	@Autowired
	private CMemberService memberService;

	public List<Member> queryMember(Integer page, Integer size, Member member) {
		System.out.println("查询会员:" + member);
		List<Member> list = memberService.queryByConditionsAndPage(page, size, member);
		return list;
	}
}
