package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberGoldRecordDao;
import com.highto.framework.web.page.ListPage;
import com.mongodb.BasicDBObject;

@Service
public class MemberGoldService {

	@Autowired
	private MemberGoldRecordDao memberGoldRecordDao;

	public ListPage findGoldRecordByMemberId(int page, int size, String memberId) {
		long amount = memberGoldRecordDao.getAmountByMemberId(memberId);
		List<MemberGoldRecordDbo> memberList = memberGoldRecordDao.findGoldRecordByMemberId(page, size, memberId);
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public void addGoldRecord(MemberGoldRecordDbo dbo) {
		memberGoldRecordDao.addGoldRecord(dbo);
	}

}
