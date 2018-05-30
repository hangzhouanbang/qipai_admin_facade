package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.AdminDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<Admin> queryByNameAndPage(int page, int size, String nickname) {
		return adminDao.queryByNameAndPage(page, size, nickname);
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);
	}

	@Override
	public Boolean deleteAdmins(String[] ids) {
		return adminDao.deleteAdmins(ids);
	}

	@Override
	public void editAdmin(Admin admin) {
		adminDao.editAdmin(admin);
	}

}
