package com.anbang.qipai.admin.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.dao.AdminDao;
import com.anbang.qipai.admin.entity.Admin;
import com.anbang.qipai.admin.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<Admin> queryByNameAndPage(int page, int rows, String name) {
		return adminDao.queryByNameAndPage(page, rows, name);
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);
	}

	@Override
	public void deleteAdmin(String id) {
		adminDao.deleteAdmin(id);
	}

	@Override
	public Admin toeditAdmin(String id) {
		return adminDao.toeditAdmin(id);
	}

	@Override
	public void editAdmin(Admin admin) {
		adminDao.editAdmin(admin);
	}

}
