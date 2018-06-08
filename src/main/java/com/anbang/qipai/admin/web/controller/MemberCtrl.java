package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Account;
import com.anbang.qipai.admin.plan.service.AccountService;
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

	@Autowired
	private AccountService accountService;

	@RequestMapping("/queryMember")
	public Map<String, Object> queryMember(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String nickname) {
		Sort sort = new Sort(new Order("id"));
		Map<String, Object> map = memberService.queryByConditionsAndPage(page, size, sort, nickname);
		return map;
	}

	@RequestMapping("/checkAccount")
	public Map<String, Object> checkAccount(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, Account account) {
		Sort sort = new Sort(new Order("id"));
		Map<String, Object> map = accountService.queryByConditionsAndPage(page.intValue(), size.intValue(), sort,
				account);
		return map;
	}
}
