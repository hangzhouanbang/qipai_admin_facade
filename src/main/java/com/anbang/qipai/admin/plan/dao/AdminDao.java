package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;

/**
 * 管理员Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface AdminDao {

	Admin verifyAdmin(String nickname, String pass);

	long getAmount();

	List<Admin> queryByConditionsAndPage(int page, int size, Sort sort, String nickname);

	void addAdmin(Admin admin);

	Boolean deleteAdmins(String[] ids);

	Boolean editAdmin(Admin admin);

	void deleteRolesById(String adminId);

	void addRoles(List<AdminRelationRole> refList);
}
