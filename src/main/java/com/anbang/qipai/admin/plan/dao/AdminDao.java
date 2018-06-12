package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

	long getAmount(Query query);

	List<Admin> queryByConditionsAndPage(Query query);

	void addAdmin(Admin admin);

	Boolean deleteAdmins(String[] ids);

	Boolean editAdmin(Query query, Update upate);

	void deleteRolesById(String adminId);

	void addRoles(List<AdminRelationRole> refList);
}
