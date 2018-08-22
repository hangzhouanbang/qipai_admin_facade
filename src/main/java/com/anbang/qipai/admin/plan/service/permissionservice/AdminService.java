package com.anbang.qipai.admin.plan.service.permissionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.dao.permissiondao.AdminDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.util.MD5Util;
import com.highto.framework.web.page.ListPage;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private RoleDao roleDao;

	public Admin verifyAdmin(String nickname, String pass) {
		pass = MD5Util.getMD5(pass, "utf-8");
		return adminDao.findAdminByNicknameAndPass(nickname, pass);
	}

	public ListPage findAdminByNickname(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, String nickname) {
		long amount = adminDao.getAmountByNickname(nickname);
		List<Admin> adminList = adminDao.findAdminByNickname(page, size, nickname);
		ListPage listPage = new ListPage(adminList, page, size, (int) amount);
		return listPage;
	}

	public Admin findAdminById(String adminId) {
		return adminDao.findAdminById(adminId);
	}

	public void addAdmin(Admin admin) {
		admin.setCreateTime(System.currentTimeMillis());
		admin.setPass(MD5Util.getMD5(admin.getPass(), "utf-8"));
		adminDao.addAdmin(admin);
	}

	public void deleteAdminByIds(String[] ids) {
		adminDao.deleteAdminByIds(ids);
	}

	public void updateAdminPass(Admin admin) {
		String pass = MD5Util.getMD5(admin.getPass(), "utf-8");
		admin.setPass(pass);
		adminDao.updateAdminPass(admin);
	}

	public void updateRole(String adminId, String[] roleIds) {
		List<Role> roleList = roleDao.findRoleById(roleIds);
		adminDao.updateRoleList(adminId, roleList);
	}
}
