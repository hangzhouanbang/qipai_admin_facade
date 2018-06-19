package com.anbang.qipai.admin.plan.dao.recorddao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.record.MemberGoldRecordDbo;

public interface MemberGoldRecordDao {

	long getAmount();

	List<MemberGoldRecordDbo> findGoldRecordById(int page, int size, Sort sort, String memberId);

	void addGoldRecord(MemberGoldRecordDbo dbo);

	Boolean deleteGoldRecordById(String[] recordIds);

	Boolean updateGoldRecordById(MemberGoldRecordDbo dbo);
}
