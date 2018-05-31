package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.Role;

/**角色Dao
 * @author 林少聪 2018.5.31
 *
 */
public interface RoleDao {

	/**查询所有角色信息
	 * @return 所有角色集合
	 */
	List<Role> getAllRoles();

	/**查询管理员所具有的角色
	 * @param roles 管理员具备的角色的id数组
	 * @return 管理员所具有的角色的集合
	 */
	List<Role> getAllRolesOfAdmin(String[] roles);
}
