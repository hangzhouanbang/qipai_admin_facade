package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.mongodb.BasicDBObject;

@Component
public class MongodbMemberDao implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<MemberDbo> findMemberDboByConditions(int page, int size, MemberDbo member) {
		Query query = new Query();
		if (member.getId() != null && !"".equals(member.getId())) {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && !"".equals(member.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getVip() != null) {
			query.addCriteria(Criteria.where("vip").is(member.getVip()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, MemberDbo.class);
	}

	@Override
	public void addMember(MemberDbo member) {
		mongoTemplate.insert(member);
	}

	@Override
	public long getAmountByConditions(MemberDbo member) {
		Query query = new Query();
		if (member.getId() != null && !"".equals(member.getId())) {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && !"".equals(member.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getVip() != null) {
			query.addCriteria(Criteria.where("vip").is(member.getVip()));
		}
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public MemberDbo findMemberById(String memberId) {
		return mongoTemplate.findById(memberId, MemberDbo.class);
	}

	@Override
	public long countNewMemberByTime(long startTime, long endTime) {
		Query query = new Query(Criteria.where("createTime").gte(startTime).lt(endTime));
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public long countVIP() {
		Query query = new Query(Criteria.where("vip").is(true));
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public long countRemain(long deviation) {
		Aggregation aggregation = Aggregation.newAggregation(MemberDbo.class,
				Aggregation.project("createTime", "lastLoginTime").andExpression("lastLoginTime-createTime")
						.as("deviation"),
				Aggregation.match(Criteria.where("deviation").gte(deviation)),
				Aggregation.group().count().as("remain"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, MemberDbo.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getLong("remain");
	}

	@Override
	public void updateMemberPhone(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("phone", member.getPhone());
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void resetMemberVip(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("vip", member.getVip());
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberVip(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("vipEndTime", member.getVipEndTime());
		update.set("vip", member.getVip());
		update.set("vipLevel", member.getVipLevel());
		update.set("vipScore", member.getVipScore());
		update.set("cost", member.getCost());
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberLogin(String memberId, String state, String loginIp, boolean vip, long lastLoginTime) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("loginIp", loginIp);
		update.set("state", state);
		update.set("vip", vip);
		update.set("lastLoginTime", lastLoginTime);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void verifyUser(String memberId, String realName, String IDcard, boolean verify) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("realName", realName);
		update.set("IDcard", IDcard);
		update.set("verifyUser", verify);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

}
