package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;
import com.highto.framework.web.page.ListPage;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

	public List<Role> findAllRolesOfAdmin(String adminId) {
		return roleDao.findAllRolesOfAdmin(adminId);
	}

	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	public void deleteRoleByIds(String[] ids) {
		roleDao.deleteAdminRelationRoleByRoleIds(ids);
		roleDao.deleteRoleRelationPrivilegesByRoleIds(ids);
		roleDao.deleteRoleByIds(ids);
	}

	public boolean updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	public ListPage findRoleByName(int page, int size, String role) {
		long amount = roleDao.getAmountByName(role);
		List<Role> roleList = roleDao.findRoleByName(page, size, role);
		ListPage listPage = new ListPage(roleList, page, size, (int) amount);
		return listPage;
	}

	public void editPrivilege(String roleId, String[] privilegeIds) {
		String[] ids = { roleId };
		roleDao.deleteRoleRelationPrivilegesByRoleIds(ids);
		List<RoleRelationPrivilege> refList = new ArrayList<RoleRelationPrivilege>();
		for (String privilegeId : privilegeIds) {
			RoleRelationPrivilege ref = new RoleRelationPrivilege();
			ref.setRoleId(roleId);
			ref.setPrivilegeId(privilegeId);
			refList.add(ref);
		}
		roleDao.addPrivileges(refList);
	}

}
