package com.anbang.qipai.admin.dao;

import java.util.List;

import com.anbang.qipai.admin.entity.Admin;

public interface AdminDao {
	List<Admin> queryAllByPage(int page, int rows);

	/** 根据管理员姓名进行分页查询
	 * @param page 当前页
	 * @param rows 每页数据条数
	 * @param name 要查询的管理员姓名
	 * @return 结果集
	 */
	List<Admin> queryByNameAndPage(int page, int rows, String nickName);

	/**添加管理員
	 * @param admin 要添加的管理员信息
	 * @return 操作結果
	 */
	Admin addAdmin(Admin admin);

	/**刪除管理員
	 * @param ids 要刪除的管理員id數組
	 */
	void deleteAdmins(String[] ids);

	/**編輯管理員
	 * @param admin 編輯後的管理員信息
	 * @return 操作結果
	 */
	Admin editAdmin(Admin admin);
}
