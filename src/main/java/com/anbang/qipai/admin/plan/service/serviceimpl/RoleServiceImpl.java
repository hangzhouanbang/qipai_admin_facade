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

import com.anbang.qipai.admin.plan.dao.RoleDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.AdminRelationRole;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.domain.RoleRelationPrivilege;
import com.anbang.qipai.admin.plan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public List<Role> getAllRolesOfAdmin(Admin admin) {
		return roleDao.getAllRolesOfAdmin(admin);
	}

	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public void deleteRoles(String[] ids) {
		Object[] idsTemp = ids;
		Query queryRef = new Query(Criteria.where("roleId").in(idsTemp));
		roleDao.deleteRoles(queryRef, AdminRelationRole.class);
		Query query = new Query(Criteria.where("id").in(idsTemp));
		roleDao.deleteRoles(query, Role.class);
	}

	@Override
	public Boolean editRole(Role role) {
		Query query = new Query(Criteria.where("id").is(role.getId()));
		Update update = new Update();
		if (role.getRole() != null) {
			update.addToSet("pass", role.getRole());
		}
		return roleDao.editRole(query, update);
	}

	@Override
	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = roleDao.getAmount(new Query());
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		Query query = new Query(Criteria.where("role").regex(role));
		if (role != null) {
			query.addCriteria(Criteria.where("role").regex(role));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		List<Role> roleList = roleDao.queryByConditionsAndPage(query);
		map.put("pageNumber", pageNumber);
		map.put("roleList", roleList);
		return map;
	}

	@Override
	public Boolean editPrivilege(String roleId, String[] privilegeIds) {
		roleDao.deleteRolesById(roleId);
		List<RoleRelationPrivilege> refList = new ArrayList<RoleRelationPrivilege>();
		for (String privilegeId : privilegeIds) {
			RoleRelationPrivilege ref = new RoleRelationPrivilege();
			ref.setRoleId(roleId);
			ref.setPrivilegeId(privilegeId);
			refList.add(ref);
		}
		roleDao.addPrivileges(refList);
		return null;
	}

}
