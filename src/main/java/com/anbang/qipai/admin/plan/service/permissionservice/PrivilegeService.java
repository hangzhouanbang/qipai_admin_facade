package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.permission.Privilege;
import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.RoleRefPrivilegeDao;
import com.highto.framework.web.page.ListPage;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Autowired
	private RoleRefPrivilegeDao roleRefPrivilegeDao;

	public List<Privilege> findAllPrivileges() {
		return privilegeDao.findAllPrivileges();
	}

	public void addPrivileges(List<Privilege> privilegeList) {
		privilegeDao.addPrivileges(privilegeList);
	}

	public void deletePrivilegeByIds(String[] ids) {
		roleRefPrivilegeDao.removeByPrivilegeIds(ids);
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
