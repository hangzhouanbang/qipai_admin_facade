package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.dao.daobase.MongodbDaoBase;
import com.anbang.qipai.admin.plan.domain.Member;

@Component
public class MongodbMemberDao extends MongodbDaoBase implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Member> queryByConditionsAndPage(int page, int size, Member member) {
		Map<String, Object> paramMap = null;
		if (member.getNickname() != null || member.getSex() != null || member.getAge() != null
				|| member.getBirthday() != null || member.getAdress() != null) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("nickname", member.getNickname());
			paramMap.put("sex", member.getSex());
			paramMap.put("age", member.getAge());
			paramMap.put("birthday", member.getBirthday());
			paramMap.put("adress", member.getAdress());
		}
		Sort sort = new Sort(new Order("id"));
		super.setMongoTemplate(mongoTemplate);
		return searchLike(paramMap, (page - 1) * size, size, sort, Member.class);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
