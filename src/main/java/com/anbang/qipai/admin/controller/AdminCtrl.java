package com.anbang.qipai.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.entity.Admin;
import com.anbang.qipai.admin.service.AdminService;

@Controller
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/queryAdmin")
	@ResponseBody
	public List<Admin> queryAdmin(Integer page, Integer rows, String name) {
		List<Admin> list = adminService.queryByNameAndPage(page, rows, name);
		for (Admin a : list) {
			System.out.println(a);
		}
		return list;
	}

	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin() {
		return "";
	}

	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public String deleteAdmin() {
		return "";
	}

	@RequestMapping("/editAdmin")
	@ResponseBody
	public String editAdmin() {
		return "";
	}
}
