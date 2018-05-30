package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Override
	public List<Privilege> getAllPrivileges() {
		return privilegeDao.getAllPrivileges();
	}

	@Override
	public List<Privilege> getAllPrivilegesOfRole(String[] privileges) {
		return privilegeDao.getAllPrivilegesOfRole(privileges);
	}

}
