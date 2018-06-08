package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.Member;
import com.mongodb.WriteResult;

@Component
public class MongodbMemberDao implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Member> queryByConditionsAndPage(Query query) {
		return mongoTemplate.find(query, Member.class);
	}

	@Override
	public void addMember(Member member) {
		mongoTemplate.insert(member);
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, Member.class);
		System.out.println("删除了" + result.getN() + "个玩家");
		System.out.println("共要删除" + ids.length + "个玩家");
		return result.getN() <= ids.length;
	}

	@Override
	public Boolean editMember(Member member) {
		return true;
	}

	@Override
	public long getAmount(Query query) {
		return mongoTemplate.count(query, Member.class);
	}

}
