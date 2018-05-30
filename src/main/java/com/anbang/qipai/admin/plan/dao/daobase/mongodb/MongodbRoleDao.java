package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.RoleDao;
import com.anbang.qipai.admin.plan.domain.Role;

@Component
public class MongodbRoleDao implements RoleDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Role> getAllRoles() {
		return mongoTemplate.findAll(Role.class);
	}

	@Override
	public List<Role> getAllRolesOfAdmin(String[] roles) {
		Object[] ids = roles;
		Query query = new Query();
		Criteria criteria = Criteria.where("id").in(ids);
		query.addCriteria(criteria);
		return mongoTemplate.find(query, Role.class);
	}

}
