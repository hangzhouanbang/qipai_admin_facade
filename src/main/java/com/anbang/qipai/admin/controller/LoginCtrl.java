package com.anbang.qipai.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginCtrl {

	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String name,String pass) {
		
		return "";
	}
}
