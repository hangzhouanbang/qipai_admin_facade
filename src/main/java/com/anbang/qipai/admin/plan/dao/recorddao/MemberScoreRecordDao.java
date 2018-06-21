package com.anbang.qipai.admin.plan.dao.recorddao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.record.MemberScoreRecordDbo;

public interface MemberScoreRecordDao {
	long getAmount();

	List<MemberScoreRecordDbo> findScoreRecordById(int page, int size, Sort sort, String memberId);

	void addScoreRecord(MemberScoreRecordDbo dbo);

	Boolean deleteScoreRecordById(String[] recordIds);

	Boolean updateScoreRecordById(MemberScoreRecordDbo dbo);
}
