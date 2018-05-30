package com.anbang.qipai.admin.plan.service;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Privilege;

public interface PrivilegeService {
	/**
	 * 查询所有权限信息
	 * 
	 * @return 所有权限集合
	 */
	List<Privilege> getAllPrivileges();

	/**
	 * 查询角色所具有的权限
	 * 
	 * @param roles
	 *            角色具备的权限的id数组
	 * @return 角色所具有的权限的集合
	 */
	List<Privilege> getAllPrivilegesOfRole(String[] privileges);
}
