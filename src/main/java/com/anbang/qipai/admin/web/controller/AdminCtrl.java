package com.anbang.qipai.admin.web.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.service.AdminService;

/**
 * 管理员Controller
 * 
 * @author 林少聪 2018.5.31
 *
 */
@RestController
@RequestMapping("/adminCtrl")
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/queryAdmin")
	public Map<String, Object> queryAdmin(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String nickname) {
		Sort sort = new Sort(new Order("id"));
		Map<String, Object> map = adminService.queryByConditionsAndPage(page.intValue(), size.intValue(), sort,
				nickname);
		return map;
	}

	@RequestMapping("/addAdmin")
	public String addAdmin(Admin admin) {
		if (admin.getNickname() == null || admin.getPass() == null) {
			return "fail";
		}
		admin.setCreateTime(new Date(System.currentTimeMillis()));
		adminService.addAdmin(admin);
		return "success";
	}

	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam(name = "id") String[] ids) {
		for (String id : ids) {
			if (id.equals("5b0d1133ceac1229f892c9ab")) {// 超级管理员无法删除
				return "fail";
			}
		}
		if (adminService.deleteAdmins(ids)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/editAdmin")
	public String editAdmin(Admin admin) {
		if (admin.getId() != null || adminService.editAdmin(admin)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/editRole")
	public void editRole(String adminId, String[] roleIds) {
		adminService.editRole(adminId, roleIds);
	}
}
