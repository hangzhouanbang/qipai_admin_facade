package com.anbang.qipai.admin.plan.service;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Role;

/**
 * 角色service
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface RoleService {

	/**
	 * 查询所有角色信息
	 * 
	 * @return 所有角色集合
	 */
	List<Role> getAllRoles();

	/**
	 * 查询管理员所具有的角色
	 * 
	 * @param roles
	 *            管理员具备的角色的id数组
	 * @return 管理员所具有的角色的集合
	 */
	List<Role> getAllRolesOfAdmin(String[] roles);

	/**
	 * 添加角色
	 * 
	 * @param role
	 *            角色信息
	 */
	void addRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param ids
	 *            要删除的角色的id数组
	 * @return 操作结果
	 */
	Boolean deleteRoles(String[] ids);

	/**
	 * 编辑角色
	 * 
	 * @param role
	 *            角色信息
	 */
	void editRole(Role role);
}
