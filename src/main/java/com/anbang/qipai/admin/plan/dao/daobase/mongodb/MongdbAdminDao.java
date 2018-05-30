package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.AdminDao;
import com.anbang.qipai.admin.plan.dao.daobase.MongodbDaoBase;
import com.anbang.qipai.admin.plan.domain.Admin;
import com.mongodb.WriteResult;

@Component
public class MongdbAdminDao extends MongodbDaoBase implements AdminDao {

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
	public List<Admin> queryByNameAndPage(int page, int size, String nickname) {
		Map<String, Object> paramMap = null;
		if (nickname != null) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("nickname", nickname);
		}
		Sort sort = new Sort(new Order("id"));
		super.setMongoTemplate(mongoTemplate);
		return searchLike(paramMap, (page - 1) * size, size, sort, Admin.class);
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
		System.out.println(result.getN());
		System.out.println(ids.length);
		return result.getN() <= ids.length;
	}

	@Override
	public void editAdmin(Admin admin) {
		Map<String, Object> inputs = new HashMap<String, Object>();
		if (admin.getNickname() != null) {
			inputs.put("nickname", admin.getNickname());
		}
		if (admin.getPass() != null) {
			inputs.put("pass", admin.getPass());
		}
		if (admin.getUser() != null) {
			inputs.put("user", admin.getUser());
		}
		if (admin.getIdCard() != null) {
			inputs.put("idCard", admin.getIdCard());
		}
		if (admin.getSex() != null) {
			inputs.put("sex", admin.getSex());
		}
		try {
			super.setMongoTemplate(mongoTemplate);
			updateMultiAttributes(admin.getId(), inputs, Admin.class);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
