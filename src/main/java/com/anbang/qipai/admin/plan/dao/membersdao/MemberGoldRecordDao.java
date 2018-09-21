package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.mongodb.BasicDBObject;

public interface MemberGoldRecordDao {

	long getAmountByMemberId(String memberId);

	List<MemberGoldRecordDbo> findGoldRecordByMemberId(int page, int size, String memberId);

	void addGoldRecord(MemberGoldRecordDbo dbo);

}
