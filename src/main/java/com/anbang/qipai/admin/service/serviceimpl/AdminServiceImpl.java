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
	public List<Admin> queryAllByPage(int page, int rows) {
		return adminDao.queryAllByPage(page, rows);
	}

	@Override
	public List<Admin> queryByNameAndPage(int page, int rows, String nickName) {
		return adminDao.queryByNameAndPage(page, rows, nickName);
	}

	@Override
	public Admin addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

	@Override
	public void deleteAdmins(String[] ids) {
		adminDao.deleteAdmins(ids);
	}

	@Override
	public Admin editAdmin(Admin admin) {
		return adminDao.editAdmin(admin);
	}

}
