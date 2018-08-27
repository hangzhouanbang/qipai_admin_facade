package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.report.GameDataReport;
import com.anbang.qipai.admin.plan.dao.GameReportDao;

@Component
public class MongodbGameReportDao implements GameReportDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<GameDataReport> findReportByTime(long startTime, long endTime, String game) {
		Query query = new Query(Criteria.where("game").is(game));
		query.addCriteria(Criteria.where("date").gte(startTime).lte(endTime));
		//需要按照date建立索引
		return mongoTemplate.find(query, GameDataReport.class);
	}

	@Override
	public void addReport(GameDataReport report) {
		mongoTemplate.insert(report);
	}

}
