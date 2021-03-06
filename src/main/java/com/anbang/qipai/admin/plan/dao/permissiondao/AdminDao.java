package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.Role;

public interface AdminDao {

	Admin findAdminByNicknameAndPass(String nickname, String pass);

	Admin findAdminById(String adminId);

	long getAmountByNickname(String nickname);

	List<Admin> findAdminByNickname(int page, int size, String nickname);

	void addAdmin(Admin admin);

	void deleteAdminByIds(String[] ids);

	void updateAdminPass(String adminId, String pass);

	void deleteRoleByRoleId(String[] roleIds);

	void updateRoleList(String adminId, List<Role> roleList);

	void updatePrivilegeByRole(String roleId, List<Privilege> privilegeList);

	void deletePrivilegeByPrivilegeId(String[] privilegeIds);
}
