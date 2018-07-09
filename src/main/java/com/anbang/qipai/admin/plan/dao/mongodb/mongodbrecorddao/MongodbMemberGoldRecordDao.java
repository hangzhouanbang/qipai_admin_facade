package com.anbang.qipai.admin.plan.dao.mongodb.mongodbrecorddao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.recorddao.MemberGoldRecordDao;
import com.anbang.qipai.admin.plan.domain.record.MemberGoldRecordDbo;

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
