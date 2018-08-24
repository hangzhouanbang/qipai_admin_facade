package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.dao.permissiondao.AdminDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.highto.framework.web.page.ListPage;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private PrivilegeDao privilegeDao;

	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	public void deleteRoleByIds(String[] ids) {
		adminDao.deleteRoleByRoleId(ids);
		roleDao.deleteRoleByIds(ids);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public ListPage findRoleByName(int page, int size, String role) {
		long amount = roleDao.getAmountByName(role);
		List<Role> roleList = roleDao.findRoleByName(page, size, role);
		ListPage listPage = new ListPage(roleList, page, size, (int) amount);
		return listPage;
	}

	public void editPrivilege(String roleId, String[] privilegeIds) {
		List<Privilege> privilegeList = privilegeDao.findPrivilegesByIds(privilegeIds);
		roleDao.updatePrivilegeList(roleId, privilegeList);
	}

}
