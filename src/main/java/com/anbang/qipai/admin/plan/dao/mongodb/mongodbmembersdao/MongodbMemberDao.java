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
		if (member.getOnlineState() != null && !"".equals(member.getOnlineState())) {
			query.addCriteria(Criteria.where("onlineState").is(member.getOnlineState()));
		}
		query.addCriteria(Criteria.where("vip").is(member.isVip()));
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
		if (member.getOnlineState() != null && !"".equals(member.getOnlineState())) {
			query.addCriteria(Criteria.where("onlineState").is(member.getOnlineState()));
		}
		query.addCriteria(Criteria.where("vip").is(member.isVip()));
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
	public long countVipMember() {
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
	public void updateMemberPhone(String memberId, String phone) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("phone", phone);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberVip(String memberId, boolean vip) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vip", vip);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void memberOrderDelive(String memberId, boolean vip, long vipEndTime, int vipLevel, double vipScore) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vipEndTime", vipEndTime);
		update.set("vip", vip);
		update.set("vipLevel", vipLevel);
		update.set("vipScore", vipScore);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberRealUser(String memberId, String realName, String IDcard, boolean verify) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("realName", realName);
		update.set("IDcard", IDcard);
		update.set("verifyUser", verify);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void rechargeVip(String memberId, boolean vip, long vipEndTime) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("vipEndTime", vipEndTime);
		update.set("vip", vip);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public void updateMemberOnlineState(String memberId, String onlineState) {
		Query query = new Query(Criteria.where("id").is(memberId));
		Update update = new Update();
		update.set("onlineState", onlineState);
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

}
