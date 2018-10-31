package com.anbang.qipai.admin.plan.dao.mongodb.mongodbsignIndao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeLog;
import com.anbang.qipai.admin.plan.dao.signindao.SignInPrizeLogDao;

@Component
public class MongodbSignInPrizeLogDao implements SignInPrizeLogDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addSignInPrizeLog(SignInPrizeLog signInPrizeLog) {
		mongoTemplate.save(signInPrizeLog);
	}

	@Override
	public List<SignInPrizeLog> querySignInPrizeLog(SignInPrizeLog signInPrizeLog, Long startTime, Long endTime) {
		Query query = new Query();
		if (signInPrizeLog.getMemberId() != null) {
			query.addCriteria(Criteria.where("memberId").is(signInPrizeLog.getMemberId()));
		}
		if (signInPrizeLog.getNickname() != null) {
			query.addCriteria(Criteria.where("nickname").is(signInPrizeLog.getNickname()));
		}
		if (startTime != null) {
			query.addCriteria(Criteria.where("createTime").gte(startTime));
		}
		if (endTime != null) {
			query.addCriteria(Criteria.where("createTime").lte(endTime));
		}
		if (signInPrizeLog.getType() != null) {
			query.addCriteria(Criteria.where("type").is(signInPrizeLog.getType()));
		}
		return mongoTemplate.find(query, SignInPrizeLog.class);
	}

}
