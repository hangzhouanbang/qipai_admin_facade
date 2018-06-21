package com.anbang.qipai.admin.plan.dao.mongodb.mongodbrecorddao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.recorddao.MemberGoldRecordDao;
import com.anbang.qipai.admin.plan.domain.record.MemberGoldRecordDbo;
import com.mongodb.WriteResult;

@Component
public class MongodbMemberGoldRecordDao implements MemberGoldRecordDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, MemberGoldRecordDbo.class);
	}

	@Override
	public List<MemberGoldRecordDbo> findGoldRecordById(int page, int size, Sort sort, String memberId) {
		Query query = new Query(Criteria.where("memberId").is(memberId));
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, MemberGoldRecordDbo.class);
	}

	@Override
	public void addGoldRecord(MemberGoldRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

	@Override
	public Boolean deleteGoldRecordById(String[] recordIds) {
		Object[] ids = recordIds;
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult writeResult = mongoTemplate.remove(query, MemberGoldRecordDbo.class);
		return writeResult.getN() <= recordIds.length;
	}

	@Override
	public Boolean updateGoldRecordById(MemberGoldRecordDbo dbo) {
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
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, MemberGoldRecordDbo.class);
		return writeResult.getN() > 0;
	}

}
