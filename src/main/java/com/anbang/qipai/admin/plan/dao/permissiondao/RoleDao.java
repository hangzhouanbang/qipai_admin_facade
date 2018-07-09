package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

/**
 * 角色Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface RoleDao {

	List<Role> findAllRoles();

	List<Role> findAllRolesOfAdmin(String adminId);

	List<Role> findRoleByName(int page, int size, String role);

	void addRole(Role role);

	boolean deleteRoles(String[] ids);

	boolean deleteAdminRelationRoles(String[] ids);

	boolean editRole(Role role);

	boolean deleteRoleRelationPrivilegesById(String roleId);

	void addPrivileges(List<RoleRelationPrivilege> refList);

	long getAmountByName(String role);
}
