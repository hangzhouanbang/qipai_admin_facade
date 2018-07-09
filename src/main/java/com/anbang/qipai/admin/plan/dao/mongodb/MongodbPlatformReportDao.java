package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.PlatformReportDao;
import com.anbang.qipai.admin.plan.domain.PlatformReport;

@Component
public class MongodbPlatformReportDao implements PlatformReportDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<PlatformReport> findReportByTime(int page, int size, long startTime, long endTime) {
		Query query = new Query(Criteria.where("date").gte(startTime).lte(endTime));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, PlatformReport.class);
	}

	@Override
	public void addReport(PlatformReport report) {
		mongoTemplate.insert(report);
	}

	@Override
	public long getAmountByTime(long startTime, long endTime) {
		Query query = new Query(Criteria.where("date").gte(startTime).lte(endTime));
		return mongoTemplate.count(query, PlatformReport.class);
	}

}
