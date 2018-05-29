package com.anbang.qipai.admin.service;

import java.util.List;

import com.anbang.qipai.admin.entity.Admin;

public interface AdminService {

	List<Admin> queryAllByPage(int page, int rows);

	/** 根据管理员姓名进行分页查询
	 * @param page 当前页
	 * @param rows 每页数据条数
	 * @param name 要查询的管理员姓名
	 * @return 结果集
	 */
	List<Admin> queryByNameAndPage(int page, int rows, String nickName);

	/**添加管理员
	 * @param admin 要添加的管理员信息
	 * @return 操作结果
	 */
	Admin addAdmin(Admin admin);

	/**删除管理员
	 * @param ids 要删除的管理员id数组
	 */
	void deleteAdmins(String[] ids);

	/**编辑管理员
	 * @param admin 编辑后的管理员信息
	 * @return 操作结果
	 */
	Admin editAdmin(Admin admin);
}
