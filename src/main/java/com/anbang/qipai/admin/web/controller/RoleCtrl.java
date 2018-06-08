package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.service.RoleService;

/**
 * 管理员角色controller
 * 
 * @author 林少聪 2018.6.4
 *
 */
@RestController
@RequestMapping("/roleCtrl")
public class RoleCtrl {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/queryRole")
	public Map<String, Object> queryRole(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String role) {
		Sort sort = new Sort(new Order("id"));
		Map<String, Object> map = roleService.queryByConditionsAndPage(page.intValue(), size.intValue(), sort, role);
		return map;
	}

	@RequestMapping("/addRole")
	public String addRole(Role role) {
		if (role.getRole() == null) {
			return "fail";
		}
		roleService.addRole(role);
		return "success";
	}

	@RequestMapping("/deleteRole")
	public String deleteRole(@RequestParam(name = "id") String[] ids) {
		for (String id : ids) {
			if (id.equals("5b0d1f6035b436197c7a5b88")) {// 超级管理员角色无法删除
				return "fail";
			}
		}
		roleService.deleteRoles(ids);
		return "success";
	}

	@RequestMapping("/editRole")
	public String editRole(Role role) {
		if (role.getId() != null || roleService.editRole(role)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/editPrivilege")
	public void editPrivilege(String roleId, String[] privilegeIds) {
		roleService.editPrivilege(roleId, privilegeIds);
	}
}
