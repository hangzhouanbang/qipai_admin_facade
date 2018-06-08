package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Admin;

/**
 * 管理员service
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface AdminService {

	Admin verifyAdmin(String nickname, String pass);

	Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname);

	void addAdmin(Admin admin);

	Boolean deleteAdmins(String[] ids);

	Boolean editAdmin(Admin admin);

	void editRole(String adminId, String[] roleIds);
}
