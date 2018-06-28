package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

@Component
public class MongodbMemberDao implements MemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<MemberDbo> queryByConditionsAndPage(int page, int size, Sort sort, MemberDbo member) {
		Query query = new Query();
		if (member.getId() != null && member.getId() != "") {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && member.getNickname() != "") {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getVip() != null) {
			query.addCriteria(Criteria.where("vip").is(member.getVip()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, MemberDbo.class);
	}

	@Override
	public void addMember(MemberDbo member) {
		mongoTemplate.insert(member);
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		Object[] idsTemp = ids;
		Query query = new Query(Criteria.where("id").in(idsTemp));
		WriteResult result = mongoTemplate.remove(query, MemberDbo.class);
		System.out.println("删除了" + result.getN() + "个玩家");
		System.out.println("共要删除" + ids.length + "个玩家");
		return result.getN() <= ids.length;
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
		if (dbo.getGold() != null) {
			update.set("gold", dbo.getGold());
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
		if (dbo.getScore() != null) {
			update.set("score", dbo.getScore());
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
			update.set("cost", dbo.getLastLoginTime());
		}
		if (dbo.getLoginIp() != null) {
			update.set("cost", dbo.getLoginIp());
		}
		if (dbo.getOnlineTime() != null) {
			update.set("cost", dbo.getOnlineTime());
		}
		mongoTemplate.updateFirst(query, update, MemberDbo.class);
	}

	@Override
	public long getAmount(MemberDbo member) {
		Query query = new Query();
		if (member.getId() != null && member.getId() != "") {
			query.addCriteria(Criteria.where("id").is(member.getId()));
		}
		if (member.getNickname() != null && member.getNickname() != "") {
			query.addCriteria(Criteria.where("nickname").regex(member.getNickname()));
		}
		if (member.getVip() != null) {
			query.addCriteria(Criteria.where("vip").is(member.getVip()));
		}
		return mongoTemplate.count(query, MemberDbo.class);
	}

	@Override
	public MemberDbo findMemberDbo(String id) {
		return mongoTemplate.findById(id, MemberDbo.class);
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

}
