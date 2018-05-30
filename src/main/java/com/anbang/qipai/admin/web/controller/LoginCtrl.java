package com.anbang.qipai.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.service.AdminService;
import com.anbang.qipai.admin.plan.service.PrivilegeService;
import com.anbang.qipai.admin.plan.service.RoleService;
import com.anbang.qipai.admin.web.vo.PrivilegeSelectedVo;
import com.anbang.qipai.admin.web.vo.RoleSelectedVo;

@Controller
public class LoginCtrl {

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;

	/**
	 * 管理员登录验证
	 * 
	 * @param nickname
	 *            昵称
	 * @param pass
	 *            密码
	 * @return 管理员权限信息
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "pass", required = true) String pass) {
		System.out.println("nickname:" + nickname + "pass:" + pass);
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = adminService.verifyAdmin(nickname, pass);
		List<RoleSelectedVo> rsvos = new ArrayList<RoleSelectedVo>();
		List<PrivilegeSelectedVo> psvos = new ArrayList<PrivilegeSelectedVo>();
		if (admin != null) {
			String[] roles = admin.getRoles();
			List<Role> roleList = roleService.getAllRoles();
			List<Role> selectedRoleList = roleService.getAllRolesOfAdmin(roles);
			for (Role role : roleList) {
				RoleSelectedVo rsvo = new RoleSelectedVo();
				rsvo.setRole(role);
				if (selectedRoleList.contains(role)) {
					rsvo.setSelected(true);
				}
				rsvos.add(rsvo);
				List<Privilege> privilegeList = privilegeService.getAllPrivileges();
				List<Privilege> selectedPrivilegeList = privilegeService.getAllPrivilegesOfRole(role.getPrivileges());
				for (Privilege privilege : privilegeList) {
					PrivilegeSelectedVo psvo = new PrivilegeSelectedVo();
					psvo.setPrivilege(privilege);
					if (selectedPrivilegeList.contains(privilege)) {
						psvo.setSelected(true);
						if (!psvos.contains(psvo)) {
							psvos.add(psvo);
						} else {
							psvos.get(getIndexOfPrivilegeSelectedVo(psvos, psvo)).setSelected(true);
						}
					}
				}
			}
		}
		map.put("admin", admin);
		map.put("roleList", rsvos);
		map.put("privilegeList", psvos);
		return map;
	}

	public int getIndexOfPrivilegeSelectedVo(List<PrivilegeSelectedVo> psvos, PrivilegeSelectedVo psvo) {
		for (int i = 0; i < psvos.size(); i++) {
			if (psvos.get(i).equals(psvo)) {
				return i;
			}
		}
		return -1;
	}
}
