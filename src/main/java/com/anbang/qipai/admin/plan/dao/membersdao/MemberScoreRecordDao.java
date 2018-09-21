package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.anbang.qipai.admin.plan.bean.members.MemberScoreRecordDbo;
import com.mongodb.BasicDBObject;

public interface MemberScoreRecordDao {
	long getAmountByMemberId(String memberId);

	List<MemberScoreRecordDbo> findScoreRecordByMemberId(int page, int size, String memberId);

	void addScoreRecord(MemberScoreRecordDbo dbo);

}
