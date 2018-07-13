package com.anbang.qipai.admin.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.permission.Admin;
import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.plan.service.permissionservice.PrivilegeService;
import com.anbang.qipai.admin.plan.service.permissionservice.RoleService;
import com.anbang.qipai.admin.web.vo.PrivilegeVo;
import com.anbang.qipai.admin.web.vo.UserVo;

/**
 * 登录controller
 * 
 * @author 林少聪 2018.5.31
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping("/login")
	public Map<String, Object> login(@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "pass", required = true) String pass, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = adminService.verifyAdmin(nickname, pass);
		Map<String, PrivilegeVo> privilegeList = new HashMap<String, PrivilegeVo>();
		UserVo user = null;
		if (admin != null) {
			List<Role> selectedRoleList = roleService.findAllRolesOfAdmin(admin.getId());
			List<Privilege> allPrivileges = privilegeService.findAllPrivileges();
			for (Privilege privilege : allPrivileges) {
				PrivilegeVo privilegevo = new PrivilegeVo();
				privilegevo.setPrivilege(privilege);
				privilegevo.setSelected(false);
				privilegeList.put(privilege.getUri(), privilegevo);
			}
			for (Role role : selectedRoleList) {
				List<Privilege> selectedPrivilegeList = privilegeService.findAllPrivilegesOfRole(role.getId());
				for (Privilege privilege : selectedPrivilegeList) {
					privilegeList.get(privilege.getUri()).setSelected(true);
				}
			}
			user = new UserVo();
			user.setAdmin(admin);
			user.setLoginTime(new Date(System.currentTimeMillis()));
			session.setAttribute("user", user);
			session.setAttribute("privilegeList", privilegeList);
		}
		map.put("user", user);
		map.put("privilegeList", privilegeList);
		return map;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		if (session.getAttribute("user") == null) {
			return "success";
		}
		return "fail";
	}
}
