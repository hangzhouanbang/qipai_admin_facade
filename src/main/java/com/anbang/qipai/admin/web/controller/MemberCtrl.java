package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.MemberService;

/**
 * 会员controller
 * 
 * @author 林少聪 2018.6.4
 *
 */
@RestController
@RequestMapping("/memberCtrl")
public class MemberCtrl {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/queryMember")
	public List<Member> queryMember(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, Member member) {
		System.out.println("查询会员:" + member);
		List<Member> list = memberService.queryByConditionsAndPage(page, size, member);
		return list;
	}
}
