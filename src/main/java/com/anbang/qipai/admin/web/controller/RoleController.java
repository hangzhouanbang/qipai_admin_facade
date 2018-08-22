package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.service.permissionservice.RoleService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 管理员角色controller
 * 
 * @author 林少聪 2018.6.4
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	/**
	 * 查询角色
	 * 
	 * @param page
	 * @param size
	 * @param role
	 * @return
	 */
	@RequestMapping("/queryrole")
	public CommonVO queryRole(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, String role) {
		CommonVO vo = new CommonVO();
		ListPage listPage = roleService.findRoleByName(page, size, role);
		vo.setSuccess(true);
		vo.setMsg("roleList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/addrole")
	public CommonVO addRole(Role role) {
		CommonVO vo = new CommonVO();
		if (role.getRole() == null) {
			vo.setSuccess(false);
			vo.setMsg("role name is null");
			return vo;
		}
		roleService.addRole(role);
		vo.setSuccess(true);
		vo.setMsg("add role success");
		return vo;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleterole")
	public CommonVO deleteRole(@RequestParam(value = "id") String[] ids) {
		CommonVO vo = new CommonVO();
		for (String id : ids) {
			if (id.equals("000000000000000000000001")) {// 超级管理员角色无法删除
				vo.setSuccess(false);
				vo.setMsg("can not delete super role");
				return vo;
			}
		}
		roleService.deleteRoleByIds(ids);
		vo.setSuccess(true);
		vo.setMsg("delete role success");
		return vo;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/updaterole")
	public CommonVO updateRole(Role role) {
		CommonVO vo = new CommonVO();
		if (role.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("roleId is null");
			return vo;
		}
		roleService.updateRole(role);
		vo.setSuccess(true);
		vo.setMsg("update role success");
		return vo;
	}

	/**
	 * 编辑角色的权限
	 * 
	 * @param roleId
	 * @param privilegeIds
	 * @return
	 */
	@RequestMapping("/editprivilege")
	public CommonVO editPrivilege(@RequestParam(required = true) String roleId,
			@RequestParam(value = "privilegeId") String[] privilegeIds) {
		CommonVO vo = new CommonVO();
		if (roleId == "000000000000000000000001") {
			vo.setSuccess(false);
			vo.setMsg("can not edit super role");
			return vo;
		}
		roleService.editPrivilege(roleId, privilegeIds);
		vo.setSuccess(true);
		vo.setMsg("edit privilege success");
		return vo;
	}

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	@RequestMapping("/queryallrole")
	public CommonVO queryAllRole() {
		CommonVO vo = new CommonVO();
		List<Role> roleList = roleService.findAllRoles();
		vo.setSuccess(true);
		vo.setMsg("roleList");
		vo.setData(roleList);
		return vo;
	}
}
