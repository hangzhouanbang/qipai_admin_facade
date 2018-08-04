package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 管理员controller
 * 
 * @author 林少聪 2018.5.31
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/queryadmin")
	public CommonVO queryAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, String nickname) {
		CommonVO vo = new CommonVO();
		ListPage listPage = adminService.findAdminByNickname(page, size, nickname);
		vo.setSuccess(true);
		vo.setMsg("adminList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/addadmin")
	public CommonVO addAdmin(Admin admin) {
		CommonVO vo = new CommonVO();
		if (admin.getNickname() == null || admin.getPass() == null || admin.getUser() == null
				|| admin.getIdCard() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		adminService.addAdmin(admin);
		vo.setSuccess(true);
		vo.setMsg("add admin success");
		return vo;
	}

	@RequestMapping("/deleteadmin")
	public CommonVO deleteAdmin(@RequestParam(value = "id") String[] ids) {
		CommonVO vo = new CommonVO();
		for (String id : ids) {
			if ("000000000000000000000001".equals(id)) {// 超级管理员无法删除
				vo.setSuccess(false);
				vo.setMsg("can not delete super admin");
			}
		}
		adminService.deleteAdminByIds(ids);
		vo.setSuccess(true);
		vo.setMsg("delete admins success");
		return vo;
	}

	@RequestMapping("/repass")
	public CommonVO repass(Admin admin) {
		CommonVO vo = new CommonVO();
		if (admin.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("adminId is null");
			return vo;
		}
		adminService.updateAdminPass(admin);
		vo.setSuccess(true);
		vo.setMsg("update admin success");
		return vo;
	}

	@RequestMapping("/editrole")
	public CommonVO editRole(@RequestParam(required = true) String adminId,
			@RequestParam(value = "roleId") String[] roleIds) {
		CommonVO vo = new CommonVO();
		if ("000000000000000000000001".equals(adminId)) {
			vo.setSuccess(false);
			vo.setMsg("can not edit super admin");
			return vo;
		}
		adminService.updateRole(adminId, roleIds);
		vo.setSuccess(true);
		vo.setMsg("edit role success");
		return vo;
	}
}
