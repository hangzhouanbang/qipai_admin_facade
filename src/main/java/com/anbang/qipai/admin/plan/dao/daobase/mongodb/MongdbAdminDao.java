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
	public List<Admin> queryByConditionsAndPage(int page, int size, Admin admin) {
		Map<String, Object> paramMap = null;
		if (admin.getNickname() != null) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("nickname", admin.getNickname());
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
		System.out.println("删除了" + result.getN() + "个管理员");
		System.out.println("共要删除" + ids.length + "个管理员");
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
		super.setMongoTemplate(mongoTemplate);
		try {
			updateMultiAttributes(admin.getId(), inputs, Admin.class);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
