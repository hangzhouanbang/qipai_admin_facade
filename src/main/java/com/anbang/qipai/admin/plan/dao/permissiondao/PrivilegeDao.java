package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.RoleRelationPrivilege;

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

	List<Privilege> findPrivilegeByName(int page, int size, String privilege);

	long getAmountByName(String privilege);

	void addPrivileges(List<Privilege> privilegeList);

	void addRoleRefPrivilege(List<RoleRelationPrivilege> refList);

	boolean deletePrivilegeByIds(String[] ids);

	boolean deleteRoleRelationPrivilegeByPrivilegeIds(String[] ids);

	boolean updatePrivilege(Privilege privilege);
}
