package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.PlatformReportDao;
import com.anbang.qipai.admin.plan.domain.PlatformReport;

@Component
public class MongodbPlatformReportDao implements PlatformReportDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<PlatformReport> findReportByTime(long startTime, long endTime) {
		Query query = new Query(Criteria.where("date").gte(startTime).lte(endTime));
		return mongoTemplate.find(query, PlatformReport.class);
	}

	@Override
	public void addReport(PlatformReport report) {
		mongoTemplate.insert(report);
	}

	@Override
	public void updateReport(PlatformReport report) {
		Query query = new Query(Criteria.where("date").is(report.getDate()));
		Update update = new Update();
		if (report.getNewMember() != null) {
			update.set("newMember", report.getNewMember());
		}
		if (report.getCost() != null) {
			update.set("cost", report.getCost());
		}
		if (report.getCurrentMember() != null) {
			update.set("currentMember", report.getCurrentMember());
		}
		if (report.getGameNum() != null) {
			update.set("gameNum", report.getGameNum());
		}
		if (report.getLoginMember() != null) {
			update.set("loginMember", report.getLoginMember());
		}
		if (report.getRemainSecond() != null) {
			update.set("remainSecond", report.getRemainSecond());
		}
		if (report.getRemainThird() != null) {
			update.set("remainThird", report.getRemainThird());
		}
		if (report.getRemainSeventh() != null) {
			update.set("remainSeventh", report.getRemainSeventh());
		}
		if (report.getRemainMonth() != null) {
			update.set("remainMonth", report.getRemainMonth());
		}
		mongoTemplate.updateFirst(query, update, PlatformReport.class);
	}

}
