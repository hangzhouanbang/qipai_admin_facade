package com.anbang.qipai.admin.plan.dao.permissiondao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;

public interface AdminDao {

	Admin verifyAdmin(String nickname, String pass);

	long getAmountByNickname(String nickname);

	List<Admin> findAdminByNickname(int page, int size, String nickname);

	void addAdmin(Admin admin);

	boolean deleteAdminByIds(String[] ids);

	boolean updateAdminPass(Admin admin);

	boolean deleteAdminRelationRolesById(String adminId);

	void addRoles(List<AdminRelationRole> refList);
}
