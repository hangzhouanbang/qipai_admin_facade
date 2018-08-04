package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.bean.permission.RoleRelationPrivilege;

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

	boolean deleteRoleByIds(String[] ids);

	boolean deleteAdminRelationRoleByRoleIds(String[] ids);

	boolean updateRole(Role role);

	boolean deleteRoleRelationPrivilegesByRoleIds(String[] ids);

	void addPrivileges(List<RoleRelationPrivilege> refList);

	long getAmountByName(String role);
}
