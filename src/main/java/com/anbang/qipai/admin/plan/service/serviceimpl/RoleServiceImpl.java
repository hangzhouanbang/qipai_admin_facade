package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.RoleDao;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public List<Role> getAllRolesOfAdmin(String[] roles) {
		return roleDao.getAllRolesOfAdmin(roles);
	}

	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public Boolean deleteRoles(String[] ids) {
		return roleDao.deleteRoles(ids);
	}

	@Override
	public void editRole(Role role) {
		roleDao.editRole(role);
	}

}
