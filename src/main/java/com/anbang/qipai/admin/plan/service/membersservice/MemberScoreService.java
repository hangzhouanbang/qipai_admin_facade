package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.membersdao.MemberScoreRecordDao;
import com.anbang.qipai.admin.plan.domain.members.MemberScoreRecordDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberScoreService {
	@Autowired
	private MemberScoreRecordDao memberScoreRecordDao;

	public ListPage findScoreRecordByMemberId(int page, int size, String memberId) {
		long amount = memberScoreRecordDao.getAmountByMemberId(memberId);
		List<MemberScoreRecordDbo> memberList = memberScoreRecordDao.findScoreRecordByMemberId(page, size, memberId);
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public void addScoreRecord(MemberScoreRecordDbo dbo) {
		memberScoreRecordDao.addScoreRecord(dbo);
	}
}