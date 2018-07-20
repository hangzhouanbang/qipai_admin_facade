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

import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.anbang.qipai.admin.plan.domain.members.MemberDbo;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

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
	public void editMember(MemberDbo dbo) {
		Query query = new Query(Criteria.where("id").is(dbo.getId()));
		Update update = new Update();
		if (dbo.getVip() != null) {
			update.set("vip", dbo.getVip());
		}
		if (dbo.getGender() != null) {
			update.set("gender", dbo.getGender());
		}
		if (dbo.getHeadimgurl() != null) {
			update.set("headimgurl", dbo.getHeadimgurl());
		}
		if (dbo.getNickname() != null) {
			update.set("nickname", dbo.getNickname());
		}
		if (dbo.getPhone() != null) {
			update.set("phone", dbo.getPhone());
		}
		if (dbo.getVipEndTime() != null) {
			update.set("vipEndTime", dbo.getVipEndTime());
		}
		if (dbo.getVipLevel() != null) {
			update.set("vipLevel", dbo.getVipLevel());
		}
		if (dbo.getVipScore() != null) {
			update.set("vipScore", dbo.getVipScore());
		}
		if (dbo.getCost() != null) {
			update.set("cost", dbo.getCost());
		}
		if (dbo.getLastLoginTime() != null) {
			update.set("lastLoginTime", dbo.getLastLoginTime());
		}
		if (dbo.getLoginIp() != null) {
			update.set("loginIp", dbo.getLoginIp());
		}
		if (dbo.getOnlineTime() != null) {
			update.set("onlineTime", dbo.getOnlineTime());
		}
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
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
	public boolean updateMemberPhone(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("phone", member.getPhone());
		WriteResult result = mongoTemplate.updateFirst(query, update, MemberDbo.class);
		return result.getN() > 0;
	}

	@Override
	public boolean resetMemberVip(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("vip", member.getVip());
		WriteResult result = mongoTemplate.updateFirst(query, update, MemberDbo.class);
		return result.getN() > 0;
	}

	@Override
	public boolean updateMemberVip(MemberDbo member) {
		Query query = new Query(Criteria.where("id").is(member.getId()));
		Update update = new Update();
		update.set("vipEndTime", member.getVipEndTime());
		update.set("vip", member.getVip());
		update.set("vipLevel", member.getVipLevel());
		update.set("vipScore", member.getVipScore());
		update.set("cost", member.getCost());
		WriteResult result = mongoTemplate.updateFirst(query, update, MemberDbo.class);
		return result.getN() > 0;
	}

}
