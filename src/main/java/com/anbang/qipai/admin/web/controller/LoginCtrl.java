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

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.service.AdminService;
import com.anbang.qipai.admin.plan.service.PrivilegeService;
import com.anbang.qipai.admin.plan.service.RoleService;
import com.anbang.qipai.admin.web.vo.PrivilegeVo;
import com.anbang.qipai.admin.web.vo.RoleVo;
import com.anbang.qipai.admin.web.vo.UserVo;

/**
 * 登录Controller
 * 
 * @author 林少聪 2018.5.31
 *
 */
@RestController
@RequestMapping("/loginCtrl")
public class LoginCtrl {

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;

	/**
	 * 验证管理员登录
	 * 
	 * @param nickname
	 *            管理员昵称
	 * @param pass
	 *            管理员密码
	 * @return 管理员信息及权限信息
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestParam(name = "nickname", required = true) String nickname,
			@RequestParam(name = "pass", required = true) String pass, HttpSession session) {
		System.out.println("nickname:" + nickname + "pass:" + pass);
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = adminService.verifyAdmin(nickname, pass);
		Map<String, RoleVo> rolevos = new HashMap<String, RoleVo>();
		Map<String, PrivilegeVo> privilegevos = new HashMap<String, PrivilegeVo>();
		UserVo user = null;
		if (admin != null) {
			List<Role> roleList = roleService.getAllRoles();
			List<Privilege> privilegeList = privilegeService.getAllPrivileges();
			String[] roles = admin.getRoles();
			List<Role> selectedRoleList = roleService.getAllRolesOfAdmin(roles);
			for (Role role : roleList) {
				if (selectedRoleList.contains(role)) {
					RoleVo rolevo = new RoleVo();
					rolevo.setRole(role);
					rolevo.setSelected(true);
					rolevos.put(role.getRole(), rolevo);
					List<Privilege> selectedPrivilegeList = privilegeService
							.getAllPrivilegesOfRole(role.getPrivileges());
					for (Privilege privilege : privilegeList) {
						if (selectedPrivilegeList.contains(privilege)) {
							PrivilegeVo privilegevo = new PrivilegeVo();
							privilegevo.setPrivilege(privilege);
							privilegevo.setSelected(true);
							privilegevos.put(privilege.getUri(), privilegevo);
						}
					}
				}
			}
			user = new UserVo();
			user.setAdmin(admin);
			user.setLoginTime(new Date(System.currentTimeMillis()));
			session.setAttribute("user", user);
			session.setAttribute("privilegeList", privilegevos);
		}
		map.put("user", user);
		map.put("roleList", rolevos);
		map.put("privilegeList", privilegevos);
		return map;
	}

	/**
	 * 注销登录
	 * 
	 * @param session
	 *            用户会话
	 * @return 操作结果
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		if (session.getAttribute("user") == null) {
			System.out.println("用户登出成功");
			return "success";
		}
		System.out.println("用户登出失败");
		return "fail";
	}
}
