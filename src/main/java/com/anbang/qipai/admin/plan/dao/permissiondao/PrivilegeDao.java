package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

/**
 * 权限Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface PrivilegeDao {

	List<Privilege> getAllPrivileges();

	List<Privilege> getAllNewPrivilege(List<String> uriList);

	List<Privilege> getAllPrivilegesOfRole(Role role);

	List<Privilege> queryByConditionsAndPage(Query query);

	long getAmount(Query query);

	void addPrivileges(List<Privilege> privilegeList);

	void addRoleRefPrivilege(List<RoleRelationPrivilege> refList);

	<T> void deletePrivileges(Query query, Class<T> entityClass);

	Boolean editPrivilege(Query query, Update update);
}
