package com.anbang.qipai.admin.plan.dao.mongodb.mongodbpermissiondao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;
import com.mongodb.WriteResult;

@Component
public class MongodbRoleDao implements RoleDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Role> findAllRoles() {
		return mongoTemplate.findAll(Role.class);
	}

	@Override
	public List<Role> findAllRolesOfAdmin(String adminId) {
		Query refQuery = new Query(Criteria.where("adminId").is(adminId));
		List<AdminRelationRole> refList = mongoTemplate.find(refQuery, AdminRelationRole.class);
		List<String> roleIdList = new ArrayList<String>();
		for (AdminRelationRole ref : refList) {
			roleIdList.add(ref.getRoleId());
		}
		Query query = new Query(Criteria.where("id").in(roleIdList));
		List<Role> roleList = mongoTemplate.find(query, Role.class);
		return roleList;
	}

	@Override
	public void addRole(Role role) {
		mongoTemplate.insert(role);
	}

	@Override
	public boolean deleteRoleByIds(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult writeResult = mongoTemplate.remove(query, Role.class);
		return writeResult.getN() <= ids.length;
	}

	@Override
	public boolean updateRole(Role role) {
		Query query = new Query(Criteria.where("id").is(role.getId()));
		Update update = new Update();
		update.set("role", role.getRole());
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Role.class);
		return writeResult.getN() > 0;
	}

	@Override
	public List<Role> findRoleByName(int page, int size, String role) {
		Query query = new Query();
		if (role != null && !"".equals(role)) {
			query.addCriteria(Criteria.where("role").regex(role));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, Role.class);
	}

	@Override
	public void addPrivileges(List<RoleRelationPrivilege> refList) {
		mongoTemplate.insert(refList, AdminRelationRole.class);
	}

	@Override
	public boolean deleteRoleRelationPrivilegesByRoleIds(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("roleId").in(idsTemp));
		WriteResult writeResult = mongoTemplate.remove(query, RoleRelationPrivilege.class);
		return writeResult.getN() <= ids.length;
	}

	@Override
	public long getAmountByName(String role) {
		Query query = new Query();
		if (role != null && !"".equals(role)) {
			query.addCriteria(Criteria.where("role").regex(role));
		}
		return mongoTemplate.count(query, Role.class);
	}

	@Override
	public boolean deleteAdminRelationRoleByRoleIds(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("roleId").in(idsTemp));
		WriteResult writeResult = mongoTemplate.remove(query, AdminRelationRole.class);
		return writeResult.getN() <= ids.length;
	}

}
