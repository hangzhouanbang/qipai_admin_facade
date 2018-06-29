package com.anbang.qipai.admin.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.AdminDao;
import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;
import com.anbang.qipai.admin.util.MD5Util;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private RoleDao RoleDao;

	public Admin verifyAdmin(String nickname, String pass) {
		pass = MD5Util.getMD5(pass, "utf-8");
		return adminDao.verifyAdmin(nickname, pass);
	}

	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = adminDao.getAmount();
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		List<Admin> adminList = adminDao.queryByConditionsAndPage(page, size, sort, nickname);
		for (Admin admin : adminList) {
			admin.setRoleList(RoleDao.getAllRolesOfAdmin(admin));
		}
		map.put("pageNumber", pageNumber);
		map.put("adminList", adminList);
		return map;
	}

	public void addAdmin(Admin admin) {
		admin.setPass(MD5Util.getMD5(admin.getPass(), "utf-8"));
		adminDao.addAdmin(admin);
	}

	public Boolean deleteAdmins(String[] ids) {
		return adminDao.deleteAdmins(ids);
	}

	public Boolean editAdmin(Admin admin) {
		return adminDao.editAdmin(admin);
	}

	public void editRole(String adminId, String[] roleIds) {
		adminDao.deleteRolesById(adminId);
		List<AdminRelationRole> refList = new ArrayList<AdminRelationRole>();
		if (roleIds != null) {
			for (String roleId : roleIds) {
				AdminRelationRole ref = new AdminRelationRole();
				ref.setAdminId(adminId);
				ref.setRoleId(roleId);
				refList.add(ref);
			}
		}
		adminDao.addRoles(refList);
	}
}
