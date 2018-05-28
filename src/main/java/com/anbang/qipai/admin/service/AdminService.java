package com.anbang.qipai.admin.service;

import java.util.List;

import com.anbang.qipai.admin.entity.Admin;

public interface AdminService {

	/**根据管理员姓名进行分页查询
	 * @param page 当前页
	 * @param rows 每页数据条数
	 * @param name 要查询的管理员姓名
	 * @return 结果集
	 */
	List<Admin> queryByNameAndPage(int page, int rows, String name);
	
	void addAdmin(Admin admin);
	
	void deleteAdmin(String id);
	
	Admin toeditAdmin(String id);
	
	void editAdmin(Admin admin);
}
