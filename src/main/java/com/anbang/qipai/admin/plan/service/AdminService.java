package com.anbang.qipai.admin.plan.service;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Admin;

/**
 * 管理员service
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface AdminService {

	/**
	 * 验证管理员登录
	 * 
	 * @param nickname
	 *            管理员账号
	 * @param pass
	 *            管理员密码
	 * @return 管理员信息
	 */
	Admin verifyAdmin(String nickname, String pass);

	/**
	 * 查询管理员并分页
	 * 
	 * @param start
	 *            当前页
	 * @param size
	 *            每页数量
	 * @param nickname
	 *            管理员昵称
	 * @return 结果集
	 */
	List<Admin> queryByConditionsAndPage(int page, int size, Admin admin);

	/**
	 * 添加管理员
	 * 
	 * @param admin
	 *            管理员信息
	 */
	void addAdmin(Admin admin);

	/**
	 * 删除管理员
	 * 
	 * @param ids
	 *            要删除的管理员id数组
	 * @return
	 */
	Boolean deleteAdmins(String[] ids);

	/**
	 * 编辑管理员
	 * 
	 * @param admin
	 *            管理员信息
	 */
	void editAdmin(Admin admin);
}
