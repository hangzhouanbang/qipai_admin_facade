package com.anbang.qipai.admin.plan.dao.mongodb.mongodbpermissiondao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.permissiondao.AdminDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;
import com.mongodb.WriteResult;

@Component
public class MongodbAdminDao implements AdminDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Admin verifyAdmin(String nickname, String pass) {
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
	public boolean deleteAdminByIds(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, Admin.class);
		return result.getN() <= ids.length;
	}

	@Override
	public boolean updateAdminPass(Admin admin) {
		Query query = new Query(Criteria.where("id").is(admin.getId()));
		Update update = new Update();
		if (admin.getPass() != null && !"".equals(admin.getPass())) {
			update.set("pass", admin.getPass());
		}
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Admin.class);
		return writeResult.getN() > 0;
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
	public boolean deleteAdminRelationRolesById(String adminId) {
		Query query = new Query(Criteria.where("adminId").is(adminId));
		WriteResult writeResult = mongoTemplate.remove(query, AdminRelationRole.class);
		return writeResult.getN() > 0;
	}

	@Override
	public void addRoles(List<AdminRelationRole> refList) {
		mongoTemplate.insert(refList, AdminRelationRole.class);
	}

}
