package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberLoginRecordDao;
import com.mongodb.BasicDBObject;

@Component
public class MongodbMemberLoginRecordDao implements MemberLoginRecordDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(MemberLoginRecord record) {
		mongoTemplate.insert(record);
	}

	@Override
	public void updateOnlineTimeById(String id, long onlineTime) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("onlineTime", onlineTime);
		mongoTemplate.updateFirst(query, update, MemberLoginRecord.class);
	}

	@Override
	public MemberLoginRecord findRecentRecordByMemberId(String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		// 需要建立索引
		Sort sort = new Sort(new Order(Direction.DESC, "loginTime"));
		query.with(sort);
		return mongoTemplate.findOne(query, MemberLoginRecord.class);
	}

	@Override
	public int countLoginMemberByTime(long startTime, long endTime) {
		Aggregation aggregation = Aggregation.newAggregation(MemberLoginRecord.class,
				Aggregation.match(Criteria.where("loginTime").gte(startTime).lte(endTime)),
				Aggregation.group("memberId").sum("id").as("loginNo"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, MemberLoginRecord.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		return list.size();
	}

	@Override
	public int countRemainMemberByDeviationTime(long deviation) {
		Aggregation aggregation = Aggregation.newAggregation(MemberLoginRecord.class,
				Aggregation.project("loginTime", "lastLoginTime").andExpression("loginTime-lastLoginTime")
						.as("deviation"),
				Aggregation.match(Criteria.where("deviation").gte(deviation)),
				Aggregation.group("memberId").count().as("remain"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, MemberLoginRecord.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getInt("remain");
	}

}
