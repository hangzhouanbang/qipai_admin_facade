package com.anbang.qipai.admin.plan.dao.mongodb.mongodbpermissiondao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.permissiondao.PrivilegeDao;
import com.anbang.qipai.admin.plan.domain.permission.Privilege;
import com.anbang.qipai.admin.plan.domain.permission.RoleRelationPrivilege;
import com.mongodb.WriteResult;

@Component
public class MongodbPrivilegeDao implements PrivilegeDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Privilege> findAllPrivileges() {
		return mongoTemplate.findAll(Privilege.class);
	}

	@Override
	public List<Privilege> findAllPrivilegesOfRole(String roleId) {
		Query refQuery = new Query(Criteria.where("roleId").is(roleId));
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
	public void addPrivileges(List<Privilege> privilegeList) {
		mongoTemplate.insert(privilegeList, Privilege.class);
	}

	@Override
	public void addRoleRefPrivilege(List<RoleRelationPrivilege> refList) {
		mongoTemplate.insert(refList, RoleRelationPrivilege.class);
	}

	@Override
	public boolean deletePrivileges(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult writeResult = mongoTemplate.remove(query, Privilege.class);
		return writeResult.getN() <= ids.length;
	}

	@Override
	public boolean updatePrivilege(Privilege privilege) {
		Query query = new Query(Criteria.where("id").is(privilege.getId()));
		Update update = new Update();
		update.set("privilege", privilege.getPrivilege());
		update.set("uri", privilege.getUri());
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Privilege.class);
		return writeResult.getN() > 0;
	}

	@Override
	public List<Privilege> findByPrivilege(int page, int size, String privilege) {
		Query query = new Query();
		if (privilege != null && !"".equals(privilege)) {
			query.addCriteria(Criteria.where("privilege").regex(privilege));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, Privilege.class);
	}

	@Override
	public long getAmountByPrivilege(String privilege) {
		Query query = new Query();
		if (privilege != null && !"".equals(privilege)) {
			query.addCriteria(Criteria.where("privilege").regex(privilege));
		}
		return mongoTemplate.count(query, Privilege.class);
	}

	@Override
	public List<Privilege> findPrivilegeByUri(List<String> uriList) {
		Query query = new Query(Criteria.where("uri").in(uriList));
		return mongoTemplate.find(query, Privilege.class);
	}

	@Override
	public boolean deleteRoleRelationPrivileges(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("privilegeId").in(idsTemp));
		WriteResult writeResult = mongoTemplate.remove(query, RoleRelationPrivilege.class);
		return writeResult.getN() <= ids.length;
	}

}