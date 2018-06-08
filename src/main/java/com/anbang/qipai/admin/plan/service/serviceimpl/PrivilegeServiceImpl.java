package com.anbang.qipai.admin.plan.service.serviceimpl;

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

import com.anbang.qipai.admin.plan.dao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.domain.RoleRelationPrivilege;
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
	public List<Privilege> getAllPrivilegesOfRole(Role role) {
		return privilegeDao.getAllPrivilegesOfRole(role);
	}

	@Override
	public Boolean addPrivileges(Privilege[] privileges) {
		List<Privilege> list = new ArrayList<Privilege>();
		for (Privilege privilege : privileges) {
			if (privilege.getPrivilege() == null || privilege.getUri() == null || !list.add(privilege)) {
				return false;
			}
		}
		privilegeDao.addPrivileges(list);
		return true;
	}

	@Override
	public void deletePrivileges(String[] ids) {
		Object[] idsTemp = ids;
		Query queryRef = new Query(Criteria.where("privilegeId").in(idsTemp));
		privilegeDao.deletePrivileges(queryRef, RoleRelationPrivilege.class);
		Query query = new Query(Criteria.where("id").in(idsTemp));
		privilegeDao.deletePrivileges(query, Privilege.class);
	}

	@Override
	public Boolean editPrivilege(Privilege privilege) {
		Query query = new Query(Criteria.where("id").is(privilege.getId()));
		Update update = new Update();
		if (privilege.getPrivilege() != null || privilege.getUri() != null) {
			update.addToSet("privilege", privilege.getPrivilege());
			update.addToSet("url", privilege.getUri());
		}
		return privilegeDao.editPrivilege(query, update);
	}

	@Override
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
