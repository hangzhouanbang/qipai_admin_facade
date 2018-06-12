package com.anbang.qipai.admin.plan.dao.mongodb.mongodbpermissiondao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.permissiondao.RoleDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;
import com.anbang.qipai.admin.plan.domain.permission.Role;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;
import com.mongodb.WriteResult;

@Component
public class MongodbRoleDao implements RoleDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Role> getAllRoles() {
		return mongoTemplate.findAll(Role.class);
	}

	@Override
	public List<Role> getAllRolesOfAdmin(Admin admin) {
		Query refQuery = new Query(Criteria.where("adminId").is(admin.getId()));
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
	public <T> void deleteRoles(Query query, Class<T> entityClass) {
		mongoTemplate.remove(query, entityClass);
	}

	@Override
	public Boolean editRole(Role role) {
		Query query = new Query(Criteria.where("id").is(role.getId()));
		Update update = new Update();
		if (role.getRole() != null) {
			update.set("role", role.getRole());
			WriteResult writeResult = mongoTemplate.updateFirst(query, update, Role.class);
			return writeResult.getN() > 0;
		}
		return false;
	}

	@Override
	public List<Role> queryByConditionsAndPage(int page, int size, Sort sort, String role) {
		Query query = new Query();
		if (role != null) {
			query.addCriteria(Criteria.where("role").regex(role));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, Role.class);
	}

	@Override
	public void addPrivileges(List<RoleRelationPrivilege> refList) {
		mongoTemplate.insert(refList, AdminRelationRole.class);
	}

	@Override
	public void deleteRolesById(String roleId) {
		Query query = new Query(Criteria.where("adminId").is(roleId));
		mongoTemplate.remove(query, RoleRelationPrivilege.class);
	}

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, Role.class);
	}

}
