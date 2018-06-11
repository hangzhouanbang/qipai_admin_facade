package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

/**
 * 角色Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface RoleDao {

	List<Role> getAllRoles();

	List<Role> getAllRolesOfAdmin(Admin admin);

	List<Role> queryByConditionsAndPage(Query query);

	void addRole(Role role);

	<T> void deleteRoles(Query query, Class<T> entityClass);

	Boolean editRole(Query query, Update update);

	void deleteRolesById(String roleId);

	void addPrivileges(List<RoleRelationPrivilege> refList);

	long getAmount(Query query);
}
