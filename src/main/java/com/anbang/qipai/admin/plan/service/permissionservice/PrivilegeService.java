package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.RoleRelationPrivilege;
import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.highto.framework.web.page.ListPage;

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

	public void addPrivileges(List<Privilege> privilegeList) {
		List<String> uriList = new ArrayList<String>();
		List<RoleRelationPrivilege> refList = new ArrayList<RoleRelationPrivilege>();
		for (Privilege privilege : privilegeList) {
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
	}

	public void deletePrivilegeByIds(String[] ids) {
		privilegeDao.deleteRoleRelationPrivilegeByPrivilegeIds(ids);
		privilegeDao.deletePrivilegeByIds(ids);
	}

	public boolean updatePrivilege(Privilege privilege) {
		return privilegeDao.updatePrivilege(privilege);
	}

	public ListPage findPrivilegeByName(int page, int size, String privilege) {
		long amount = privilegeDao.getAmountByName(privilege);
		List<Privilege> privilegeList = privilegeDao.findPrivilegeByName(page, size, privilege);
		ListPage listPage = new ListPage(privilegeList, page, size, (int) amount);
		return listPage;
	}

}
