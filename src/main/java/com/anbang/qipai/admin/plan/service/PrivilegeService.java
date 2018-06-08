package com.anbang.qipai.admin.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;

/**
 * 权限service
 * 
 * @author 林少聪 2018.5.31
 *
 */
public interface PrivilegeService {

	List<Privilege> getAllPrivileges();

	List<Privilege> getAllPrivilegesOfRole(Role role);

	Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String privilege);

	Boolean addPrivileges(Privilege[] privileges);

	void deletePrivileges(String[] ids);

	Boolean editPrivilege(Privilege Privilege);
}
