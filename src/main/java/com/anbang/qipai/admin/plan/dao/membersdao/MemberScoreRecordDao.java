package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.members.MemberScoreRecordDbo;

public interface MemberScoreRecordDao {
	long getAmountByMemberId(String memberId);

	List<MemberScoreRecordDbo> findScoreRecordByMemberId(int page, int size, String memberId);

	void addScoreRecord(MemberScoreRecordDbo dbo);

}