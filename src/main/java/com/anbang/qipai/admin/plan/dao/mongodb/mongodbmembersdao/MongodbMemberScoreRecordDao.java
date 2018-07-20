package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.membersdao.MemberScoreRecordDao;
import com.anbang.qipai.admin.plan.domain.members.MemberScoreRecordDbo;

@Component
public class MongodbMemberScoreRecordDao implements MemberScoreRecordDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public long getAmountByMemberId(String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		return mongoTemplate.count(query, MemberScoreRecordDbo.class);
	}

	@Override
	public List<MemberScoreRecordDbo> findScoreRecordByMemberId(int page, int size, String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, MemberScoreRecordDbo.class);
	}

	@Override
	public void addScoreRecord(MemberScoreRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

}
