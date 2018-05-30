package com.anbang.qipai.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginCtrl {

	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String nickname, String pass) {
		System.out.println("nickname:" + nickname + "pass:" + pass);
		if (nickname != null || pass != null) {
			return "success";
		}
		return "fail";
	}
}
