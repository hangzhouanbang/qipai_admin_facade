package com.anbang.qipai.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminCtrl {

	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin() {
		return "";
	}

	@RequestMapping("/editAdmin")
	@ResponseBody
	public String editAdmin() {
		return "";
	}
}
