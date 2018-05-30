package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Admin;

public interface AdminDao {

	/**查询管理员并分页
	 * @param start 当前页
	 * @param size 每页数量
	 * @param nickname 管理员昵称
	 * @return 结果集
	 */
	List<Admin> queryByNameAndPage(int page, int size, String nickname);

	/**添加管理员
	 * @param admin 管理员信息
	 */
	void addAdmin(Admin admin);

	/**删除管理员
	 * @param ids 要删除的管理员id数组
	 * @return
	 */
	Boolean deleteAdmins(String[] ids);

	/**编辑管理员
	 * @param admin 管理员信息
	 */
	void editAdmin(Admin admin);
}
