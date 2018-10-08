package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.ArrayList;
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
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBObject;

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
		List<DBObject> pipeline = new ArrayList<>();
		long loginTime = System.currentTimeMillis() - deviation;
		BasicDBObject project = new BasicDBObject();
		project.put("memberId", "$memberId");
		project.put("loginTime", "$loginTime");
		List<Object> subtract = new ArrayList<>();
		subtract.add("$loginTime");
		subtract.add(loginTime);
		BasicDBObject val = new BasicDBObject("$subtract", subtract);
		project.put("val", val);
		DBObject queryProject = new BasicDBObject("$project", project);
		pipeline.add(queryProject);

		DBObject queryMatch = new BasicDBObject("$match", new BasicDBObject("val", new BasicDBObject("$gt", 0)));
		pipeline.add(queryMatch);

		BasicDBObject group = new BasicDBObject();
		group.put("_id", "$memberId");
		group.put("no", new BasicDBObject("$sum", 1));
		DBObject queryGroup = new BasicDBObject("$group", group);
		pipeline.add(queryGroup);

		DBObject queryCount = new BasicDBObject("$count", "num");
		pipeline.add(queryCount);

		Cursor cursor = mongoTemplate.getCollection("memberLoginRecord").aggregate(pipeline,
				AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build());
		try {
			return (int) cursor.next().get("num");
		} catch (Exception e) {
			return 0;
		}
	}

}
