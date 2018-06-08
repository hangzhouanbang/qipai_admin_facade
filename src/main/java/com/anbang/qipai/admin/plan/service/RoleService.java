package com.anbang.qipai.admin.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.Role;

/**
 * 角色service
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface RoleService {

	List<Role> getAllRoles();

	List<Role> getAllRolesOfAdmin(Admin admin);

	Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String role);

	void addRole(Role role);

	void deleteRoles(String[] ids);

	Boolean editRole(Role role);

	Boolean editPrivilege(String roleId, String[] privilegeIds);
}
