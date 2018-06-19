package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.recorddao.MemberGoldRecordDao;
import com.anbang.qipai.admin.plan.domain.record.MemberGoldRecordDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberGoldService {

	@Autowired
	private MemberGoldRecordDao memberGoldRecordDao;

	public ListPage findGoldRecordById(int page, int size, Sort sort, String memberId) {
		long amount = memberGoldRecordDao.getAmount();
		long pageNum = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		List<MemberGoldRecordDbo> memberList = memberGoldRecordDao.findGoldRecordById(page, size, sort, memberId);
		ListPage listPage = new ListPage(memberList, (int) pageNum, size, (int) amount);
		return listPage;
	}

	public void addGoldRecord(MemberGoldRecordDbo dbo) {
		memberGoldRecordDao.addGoldRecord(dbo);
	}
}
