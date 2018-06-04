package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.RoleDao;
import com.anbang.qipai.admin.plan.dao.daobase.MongodbDaoBase;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.Role;
import com.mongodb.WriteResult;

@Component
public class MongodbRoleDao extends MongodbDaoBase implements RoleDao {

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

	@Override
	public void addRole(Role role) {
		mongoTemplate.insert(role);
	}

	@Override
	public Boolean deleteRoles(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, Role.class);
		System.out.println("删除了" + result.getN() + "个角色");
		System.out.println("共要删除" + ids.length + "个角色");
		return result.getN() <= ids.length;
	}

	@Override
	public void editRole(Role role) {
		Map<String, Object> inputs = new HashMap<String, Object>();
		if (role.getRole() != null) {
			inputs.put("role", role.getRole());
		}
		if (role.getPrivileges() != null) {
			inputs.put("privileges", role.getPrivileges());
		}
		super.setMongoTemplate(mongoTemplate);
		try {
			updateMultiAttributes(role.getId(), inputs, Admin.class);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
