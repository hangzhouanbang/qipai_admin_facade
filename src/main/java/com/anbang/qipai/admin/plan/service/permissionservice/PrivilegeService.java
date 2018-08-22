package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.highto.framework.web.page.ListPage;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Autowired
	private RoleDao roleDao;

	public List<Privilege> findAllPrivileges() {
		return privilegeDao.findAllPrivileges();
	}

	public void addPrivileges(List<Privilege> privilegeList) {
		List<String> uriList = new ArrayList<String>();
		for (Privilege privilege : privilegeList) {
			uriList.add(privilege.getUri());
		}
		privilegeDao.addPrivileges(privilegeList);
		List<Privilege> list = privilegeDao.findPrivilegeByUri(uriList);
		Role role = roleDao.findRoleById("000000000000000000000001");
		List<Privilege> rplist = role.getPrivilegeList();
		for (Privilege privilege : list) {
			rplist.forEach((p) -> {
				if (!p.getId().equals(privilege.getId())) {
					rplist.add(privilege);
				}
			});
		}
		roleDao.updatePrivilegeList("000000000000000000000001", rplist);
	}

	public void deletePrivilegeByIds(String[] ids) {
		roleDao.deletePrivilegeByPrivilegeId(ids);
		privilegeDao.deletePrivilegeByIds(ids);
	}

	public void updatePrivilege(Privilege privilege) {
		privilegeDao.updatePrivilege(privilege);
	}

	public ListPage findPrivilegeByName(int page, int size, String privilege) {
		long amount = privilegeDao.getAmountByName(privilege);
		List<Privilege> privilegeList = privilegeDao.findPrivilegeByName(page, size, privilege);
		ListPage listPage = new ListPage(privilegeList, page, size, (int) amount);
		return listPage;
	}

}
