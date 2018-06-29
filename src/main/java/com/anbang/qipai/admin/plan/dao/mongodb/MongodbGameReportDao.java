package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.GameReportDao;
import com.anbang.qipai.admin.plan.domain.GameDataReport;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.mongodb.BasicDBObject;

@Component
public class MongodbGameReportDao implements GameReportDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<GameDataReport> findReportByTime(long startTime, long endTime, String game) {
		Query query = new Query(Criteria.where("date").gte(startTime).lte(endTime));
		query.addCriteria(Criteria.where("game").is(game));
		return mongoTemplate.find(query, GameDataReport.class);
	}

	@Override
	public void addReport(GameDataReport report) {
		mongoTemplate.insert(report);
	}

	@Override
	public void updateReport(GameDataReport report) {
		Query query = new Query(Criteria.where("date").is(report.getDate()));
		Update update = new Update();
		if (report.getCurrentMember() != null) {
			update.set("currentMember", report.getCurrentMember());
		}
		if (report.getGameNum() != null) {
			update.set("gameNum", report.getGameNum());
		}
		if (report.getLoginMember() != null) {
			update.set("loginMember", report.getLoginMember());
		}
		mongoTemplate.updateFirst(query, update, GameDataReport.class);
	}

	@Override
	public long countGameNumByTime(long date) {
		Aggregation aggregation = Aggregation.newAggregation(MemberDbo.class,
				Aggregation.match(Criteria.where("date").is(date)), Aggregation.group().sum("gameNum").as("gameNum"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, GameDataReport.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getLong("gameNum");
	}

	@Override
	public long countLoginMemberByTime(long date) {
		Aggregation aggregation = Aggregation.newAggregation(MemberDbo.class,
				Aggregation.match(Criteria.where("date").is(date)),
				Aggregation.group().sum("loginMember").as("loginMember"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, GameDataReport.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getLong("loginMember");
	}

}
