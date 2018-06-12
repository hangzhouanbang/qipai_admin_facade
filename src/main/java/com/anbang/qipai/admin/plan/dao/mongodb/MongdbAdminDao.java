package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.AdminDao;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.permission.AdminRelationRole;
import com.mongodb.WriteResult;

@Component
public class MongdbAdminDao implements AdminDao {

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
	public List<Admin> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Query query = new Query();
		if (nickname != null) {
			query.addCriteria(Criteria.where("nickname").regex(nickname));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, Admin.class);
	}

	@Override
	public void addAdmin(Admin admin) {
		mongoTemplate.insert(admin);
	}

	@Override
	public Boolean deleteAdmins(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, Admin.class);
		return result.getN() <= ids.length;
	}

	@Override
	public Boolean editAdmin(Admin admin) {
		Query query = new Query(Criteria.where("id").is(admin.getId()));
		Update update = new Update();
		if (admin.getPass() != null) {
			update.set("pass", admin.getPass());
			WriteResult writeResult = mongoTemplate.updateFirst(query, update, Admin.class);
			return writeResult.getN() > 0;
		}
		return false;
	}

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, Admin.class);
	}

	@Override
	public void deleteRolesById(String adminId) {
		Query query = new Query(Criteria.where("adminId").is(adminId));
		mongoTemplate.remove(query, AdminRelationRole.class);
	}

	@Override
	public void addRoles(List<AdminRelationRole> refList) {
		mongoTemplate.insert(refList, AdminRelationRole.class);
	}

}
