package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

/**
 * 权限Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface PrivilegeDao {

	List<Privilege> findAllPrivileges();

	List<Privilege> findPrivilegeByUri(List<String> uriList);

	List<Privilege> findAllPrivilegesOfRole(String roleId);

	List<Privilege> findByPrivilege(int page, int size, String privilege);

	long getAmountByPrivilege(String privilege);

	void addPrivileges(List<Privilege> privilegeList);

	void addRoleRefPrivilege(List<RoleRelationPrivilege> refList);

	boolean deletePrivileges(String[] ids);

	boolean deleteRoleRelationPrivileges(String[] ids);

	boolean updatePrivilege(Privilege privilege);
}
