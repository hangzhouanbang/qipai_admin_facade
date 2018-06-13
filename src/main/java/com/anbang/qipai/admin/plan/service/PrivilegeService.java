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

import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;

@Service
public class PrivilegeService{

	@Autowired
	private PrivilegeDao privilegeDao;


	public List<Privilege> getAllPrivileges() {
		return privilegeDao.getAllPrivileges();
	}

	public List<Privilege> getAllPrivilegesOfRole(Role role) {
		return privilegeDao.getAllPrivilegesOfRole(role);
	}

	public Boolean addPrivileges(List<Privilege> privilegeList) {
		List<String> uriList = new ArrayList<String>();
		;
		List<RoleRelationPrivilege> refList = new ArrayList<RoleRelationPrivilege>();
		for (Privilege privilege : privilegeList) {
			if (privilege.getPrivilege() == null || privilege.getUri() == null) {
				return false;
			}
			uriList.add(privilege.getUri());
		}
		privilegeDao.addPrivileges(privilegeList);
		List<Privilege> list = privilegeDao.getAllNewPrivilege(uriList);
		for (Privilege privilege : list) {
			RoleRelationPrivilege ref = new RoleRelationPrivilege();
			ref.setRoleId("5b0d1f6035b436197c7a5b88");
			ref.setPrivilegeId(privilege.getId());
			refList.add(ref);
		}
		privilegeDao.addRoleRefPrivilege(refList);
		return true;
	}

	public void deletePrivileges(String[] ids) {
		Object[] idsTemp = ids;
		Query queryRef = new Query(Criteria.where("privilegeId").in(idsTemp));
		privilegeDao.deletePrivileges(queryRef, RoleRelationPrivilege.class);
		Query query = new Query(Criteria.where("id").in(idsTemp));
		privilegeDao.deletePrivileges(query, Privilege.class);
	}

	public Boolean editPrivilege(Privilege privilege) {
		Query query = new Query(Criteria.where("id").is(privilege.getId()));
		Update update = new Update();
		if (privilege.getPrivilege() != null && privilege.getUri() != null) {
			update.set("privilege", privilege.getPrivilege());
			update.set("uri", privilege.getUri());
			return privilegeDao.editPrivilege(query, update);
		}
		return false;
	}

	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String privilege) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = privilegeDao.getAmount(new Query());
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		Query query = new Query();
		if (privilege != null) {
			query.addCriteria(Criteria.where("privilege").regex(privilege));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		List<Privilege> privilegeList = privilegeDao.queryByConditionsAndPage(query);
		map.put("pageNumber", pageNumber);
		map.put("privilegeList", privilegeList);
		return map;
	}

}
