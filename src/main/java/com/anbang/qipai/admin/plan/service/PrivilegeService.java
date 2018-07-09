package com.anbang.qipai.admin.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	public List<Privilege> findAllPrivileges() {
		return privilegeDao.findAllPrivileges();
	}

	public List<Privilege> findAllPrivilegesOfRole(String roleId) {
		return privilegeDao.findAllPrivilegesOfRole(roleId);
	}

	public Boolean addPrivileges(List<Privilege> privilegeList) {
		List<String> uriList = new ArrayList<String>();
		List<RoleRelationPrivilege> refList = new ArrayList<RoleRelationPrivilege>();
		for (Privilege privilege : privilegeList) {
			if (privilege.getPrivilege() == null || privilege.getUri() == null) {
				return false;
			}
			uriList.add(privilege.getUri());
		}
		privilegeDao.addPrivileges(privilegeList);
		List<Privilege> list = privilegeDao.findPrivilegeByUri(uriList);
		for (Privilege privilege : list) {
			RoleRelationPrivilege ref = new RoleRelationPrivilege();
			ref.setRoleId("000000000000000000000001");
			ref.setPrivilegeId(privilege.getId());
			refList.add(ref);
		}
		privilegeDao.addRoleRefPrivilege(refList);
		return true;
	}

	public void deletePrivileges(String[] ids) {
		privilegeDao.deleteRoleRelationPrivileges(ids);
		privilegeDao.deletePrivileges(ids);
	}

	public boolean updatePrivilege(Privilege privilege) {
		return privilegeDao.updatePrivilege(privilege);
	}

	public Map<String, Object> findByPrivilege(int page, int size, String privilege) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = privilegeDao.getAmountByPrivilege(privilege);
		long pageNumber = (amount % size > 0) ? (amount / size + 1) : (amount / size);
		List<Privilege> privilegeList = privilegeDao.findByPrivilege(page, size, privilege);
		map.put("pageNumber", pageNumber);
		map.put("privilegeList", privilegeList);
		return map;
	}

}
