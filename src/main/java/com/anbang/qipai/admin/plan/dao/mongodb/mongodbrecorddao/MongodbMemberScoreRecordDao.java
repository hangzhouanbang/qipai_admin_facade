package com.anbang.qipai.admin.plan.dao.mongodb.mongodbrecorddao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.recorddao.MemberScoreRecordDao;
import com.anbang.qipai.admin.plan.domain.record.MemberScoreRecordDbo;
import com.mongodb.WriteResult;

@Component
public class MongodbMemberScoreRecordDao implements MemberScoreRecordDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, MemberScoreRecordDbo.class);
	}

	@Override
	public List<MemberScoreRecordDbo> findScoreRecordById(int page, int size, Sort sort, String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, MemberScoreRecordDbo.class);
	}

	@Override
	public void addScoreRecord(MemberScoreRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

	@Override
	public Boolean deleteScoreRecordById(String[] recordIds) {
		Object[] ids = recordIds;
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult writeResult = mongoTemplate.remove(query, MemberScoreRecordDbo.class);
		return writeResult.getN() <= recordIds.length;
	}

	@Override
	public Boolean updateScoreRecordById(MemberScoreRecordDbo dbo) {
		Query query = new Query(Criteria.where("id").is(dbo.getId()));
		Update update = new Update();
		if (dbo.getAccountId() != null) {
			update.set("accountId", dbo.getAccountId());
		}
		if (dbo.getMemberId() != null) {
			update.set("memberId", dbo.getMemberId());
		}
		if (dbo.getAccountingNo() != null) {
			update.set("accountingNo", dbo.getAccountingNo());
		}
		if (dbo.getAccountingAmount() != null) {
			update.set("accountingAmount", dbo.getAccountingAmount());
		}
		if (dbo.getBalanceAfter() != null) {
			update.set("balanceAfter", dbo.getBalanceAfter());
		}
		if (dbo.getSummary() != null) {
			update.set("summary", dbo.getSummary());
		}
		if (dbo.getAccountingTime() != null) {
			update.set("accountingTime", dbo.getAccountingTime());
		}
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, MemberScoreRecordDbo.class);
		return writeResult.getN() > 0;
	}

}
