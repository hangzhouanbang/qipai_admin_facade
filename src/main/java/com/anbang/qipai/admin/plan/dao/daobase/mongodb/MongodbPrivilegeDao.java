package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.Privilege;

@Component
public class MongodbPrivilegeDao implements PrivilegeDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Privilege> getAllPrivileges() {
		return mongoTemplate.findAll(Privilege.class);
	}

	@Override
	public List<Privilege> getAllPrivilegesOfRole(String[] privileges) {
		Object[] ids = privileges;
		Query query = new Query();
		Criteria criteria = Criteria.where("id").in(ids);
		query.addCriteria(criteria);
		return mongoTemplate.find(query, Privilege.class);
	}

}