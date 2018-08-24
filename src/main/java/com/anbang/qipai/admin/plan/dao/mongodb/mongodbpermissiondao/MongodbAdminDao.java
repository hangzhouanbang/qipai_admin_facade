package com.anbang.qipai.admin.plan.dao.mongodb.mongodbpermissiondao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.permission.Role;
import com.anbang.qipai.admin.plan.dao.permissiondao.AdminDao;

@Component
public class MongodbAdminDao implements AdminDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Admin findAdminByNicknameAndPass(String nickname, String pass) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("nickname").is(nickname);
		criteria.and("pass").is(pass);
		query.addCriteria(criteria);
		return mongoTemplate.findOne(query, Admin.class);
	}

	@Override
	public List<Admin> findAdminByNickname(int page, int size, String nickname) {
		Query query = new Query();
		if (nickname != null && !"".equals(nickname)) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, Admin.class);
	}

	@Override
	public void addAdmin(Admin admin) {
		mongoTemplate.insert(admin);
	}

	@Override
	public void deleteAdminByIds(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		mongoTemplate.remove(query, Admin.class);
	}

	@Override
	public void updateAdminPass(String adminId, String pass) {
		Query query = new Query(Criteria.where("id").is(adminId));
		Update update = new Update();
		update.set("pass", pass);
		mongoTemplate.updateFirst(query, update, Admin.class);
	}

	@Override
	public long getAmountByNickname(String nickname) {
		Query query = new Query();
		if (nickname != null && !"".equals(nickname)) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		return mongoTemplate.count(query, Admin.class);
	}

	@Override
	public void deleteRoleByRoleId(String[] roleIds) {
		Object[] ids = roleIds;
		Query query = new Query(Criteria.where("roleList.id").in(ids));
		Update update = new Update();
		update.pullAll("roleList", ids);
		mongoTemplate.updateMulti(query, update, Role.class);
	}

	@Override
	public void updateRoleList(String adminId, List<Role> roleList) {
		Query query = new Query(Criteria.where("id").in(adminId));
		Update update = new Update();
		update.set("roleList", roleList);
		mongoTemplate.updateFirst(query, update, Role.class);
	}

	@Override
	public Admin findAdminById(String adminId) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("id").is(adminId);
		query.addCriteria(criteria);
		return mongoTemplate.findOne(query, Admin.class);
	}

}
