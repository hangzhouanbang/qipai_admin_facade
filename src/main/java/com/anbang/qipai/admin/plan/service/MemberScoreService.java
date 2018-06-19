package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.recorddao.MemberScoreRecordDao;
import com.anbang.qipai.admin.plan.domain.record.MemberScoreRecordDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberScoreService {
	@Autowired
	private MemberScoreRecordDao memberScoreRecordDao;

	public ListPage findScoreRecordById(int page, int size, Sort sort, String memberId) {
		long amount = memberScoreRecordDao.getAmount();
		long pageNum = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		List<MemberScoreRecordDbo> memberList = memberScoreRecordDao.findScoreRecordById(page, size, sort, memberId);
		ListPage listPage = new ListPage(memberList, (int) pageNum, size, (int) amount);
		return listPage;
	}

	public void addScoreRecord(MemberScoreRecordDbo dbo) {
		memberScoreRecordDao.addScoreRecord(dbo);
	}
}
