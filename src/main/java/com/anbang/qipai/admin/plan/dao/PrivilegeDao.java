package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;

/**
 * 权限Dao
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface PrivilegeDao {

	List<Privilege> getAllPrivileges();

	List<Privilege> getAllPrivilegesOfRole(Role role);

	List<Privilege> queryByConditionsAndPage(Query query);

	long getAmount(Query query);

	void addPrivileges(List<Privilege> list);

	<T> void deletePrivileges(Query query, Class<T> entityClass);

	Boolean editPrivilege(Query query, Update update);
}
