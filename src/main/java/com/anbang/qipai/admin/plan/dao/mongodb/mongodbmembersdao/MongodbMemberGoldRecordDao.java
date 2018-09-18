package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberGoldRecordDao;
import com.mongodb.BasicDBObject;

@Component
public class MongodbMemberGoldRecordDao implements MemberGoldRecordDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public long getAmountByMemberId(String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		return mongoTemplate.count(query, MemberGoldRecordDbo.class);
	}

	@Override
	public List<MemberGoldRecordDbo> findGoldRecordByMemberId(int page, int size, String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, MemberGoldRecordDbo.class);
	}

	@Override
	public void addGoldRecord(MemberGoldRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

}
