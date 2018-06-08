package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.Privilege;
import com.anbang.qipai.admin.plan.domain.Role;
import com.anbang.qipai.admin.plan.domain.RoleRelationPrivilege;
import com.mongodb.WriteResult;

@Component
public class MongodbPrivilegeDao implements PrivilegeDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Privilege> getAllPrivileges() {
		return mongoTemplate.findAll(Privilege.class);
	}

	@Override
	public List<Privilege> getAllPrivilegesOfRole(Role role) {
		Query refQuery = new Query(Criteria.where("roleId").is(role.getId()));
		List<RoleRelationPrivilege> refList = mongoTemplate.find(refQuery, RoleRelationPrivilege.class);
		List<String> privilegeIdList = new ArrayList<String>();
		for (RoleRelationPrivilege ref : refList) {
			privilegeIdList.add(ref.getPrivilegeId());
		}
		Query query = new Query(Criteria.where("id").in(privilegeIdList));
		List<Privilege> privilegeList = mongoTemplate.find(query, Privilege.class);
		return privilegeList;
	}

	@Override
	public void addPrivileges(List<Privilege> list) {
		mongoTemplate.insert(list);
	}

	@Override
	public <T> void deletePrivileges(Query query, Class<T> entityClass) {
		mongoTemplate.remove(query, entityClass);
	}

	@Override
	public Boolean editPrivilege(Query query, Update update) {
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Privilege.class);
		return writeResult.getN() > 0;
	}

	@Override
	public List<Privilege> queryByConditionsAndPage(Query query) {
		return mongoTemplate.find(query, Privilege.class);
	}

	@Override
	public long getAmount(Query query) {
		return mongoTemplate.count(query, Privilege.class);
	}

}