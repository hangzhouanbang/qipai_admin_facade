package com.anbang.qipai.admin.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

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

	public void deleteRoles(String[] ids) {
		roleDao.deleteAdminRelationRoles(ids);
		roleDao.deleteRoles(ids);
	}

	public Boolean editRole(Role role) {
		return roleDao.editRole(role);
	}

	public Map<String, Object> findRoleByName(int page, int size, String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = roleDao.getAmountByName(role);
		long pageNumber = (amount % size > 0) ? (amount / size + 1) : (amount / size);
		List<Role> roleList = roleDao.findRoleByName(page, size, role);
		map.put("pageNumber", pageNumber);
		map.put("roleList", roleList);
		return map;
	}

	public void editPrivilege(String roleId, String[] privilegeIds) {
		roleDao.deleteRoleRelationPrivilegesById(roleId);
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
