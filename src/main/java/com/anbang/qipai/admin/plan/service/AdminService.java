package com.anbang.qipai.admin.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.AdminDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;

@Service
public class AdminService  {

	@Autowired
	private AdminDao adminDao;

	public Admin verifyAdmin(String nickname, String pass) {
		return adminDao.verifyAdmin(nickname, pass);
	}

	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = adminDao.getAmount(new Query());
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		Query query = new Query();
		if (nickname != null) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		List<Admin> adminList = adminDao.queryByConditionsAndPage(query);
		map.put("pageNumber", pageNumber);
		map.put("adminList", adminList);
		return map;
	}

	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);
	}

	public Boolean deleteAdmins(String[] ids) {
		return adminDao.deleteAdmins(ids);
	}

	public Boolean editAdmin(Admin admin) {
		Query query = new Query(Criteria.where("id").is(admin.getId()));
		Update update = new Update();
		if (admin.getPass() != null) {
			update.set("pass", admin.getPass());
			return adminDao.editAdmin(query, update);
		}
		return false;
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
