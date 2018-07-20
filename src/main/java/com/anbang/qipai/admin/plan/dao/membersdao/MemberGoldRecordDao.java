package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.members.MemberGoldRecordDbo;

public interface MemberGoldRecordDao {

	long getAmountByMemberId(String memberId);

	List<MemberGoldRecordDbo> findGoldRecordByMemberId(int page, int size, String memberId);

	void addGoldRecord(MemberGoldRecordDbo dbo);

}
