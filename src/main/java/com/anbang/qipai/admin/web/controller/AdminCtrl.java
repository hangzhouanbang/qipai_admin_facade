package com.anbang.qipai.admin.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.service.AdminService;

@Controller
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	/**
	 * 查询管理员并分页
	 * 
	 * @param start
	 *            当前页
	 * @param size
	 *            每页数量
	 * @param nickname
	 *            管理员昵称
	 * @return 结果集
	 */
	@RequestMapping("/queryAdmin")
	@ResponseBody
	public List<Admin> queryAdmin(Integer page, Integer size, String nickname) {
		System.out.println(nickname);
		List<Admin> list = adminService.queryByNameAndPage(page.intValue(), size.intValue(), nickname);
		return list;
	}

	/**
	 * 添加管理员
	 * 
	 * @param admin
	 *            管理员信息
	 * @return 操作结果
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin(Admin admin) {
		System.out.println(admin);
		if (admin.getPass() == null || admin.getNickname() == null || admin.getUser() == null
				|| admin.getIdCard() == null) {
			return "fail";
		}
		admin.setCreateTime(new Date(System.currentTimeMillis()));
		adminService.addAdmin(admin);
		return "success";
	}

	/**
	 * 删除管理员
	 * 
	 * @param ids
	 *            要删除的管理员id数组
	 * @return 操作结果
	 */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public String deleteAdmin(@RequestParam("id") String[] ids) {
		for (String s : ids) {
			System.out.println(s);
		}
		if (adminService.deleteAdmins(ids)) {
			return "success";
		}
		return "fail";
	}

	/**
	 * 编辑管理员
	 * 
	 * @param admin
	 *            管理员信息
	 * @return 操作结果
	 */
	@RequestMapping("/editAdmin")
	@ResponseBody
	public String editAdmin(Admin admin) {
		System.out.println(admin);
		if (admin.getId() == null) {
			return "fail";
		}
		adminService.editAdmin(admin);
		return "success";
	}
}
