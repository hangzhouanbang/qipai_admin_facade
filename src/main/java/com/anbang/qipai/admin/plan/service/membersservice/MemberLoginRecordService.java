package com.anbang.qipai.admin.plan.service.membersservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberLoginRecordDao;

@Service
public class MemberLoginRecordService {

	@Autowired
	private MemberLoginRecordDao memberLoginRecordDao;

	public void save(MemberLoginRecord record) {
		memberLoginRecordDao.save(record);
	}

	public void updateOnlineTimeById(String id, long onlineTime) {
		memberLoginRecordDao.updateOnlineTimeById(id, onlineTime);
	}

	public MemberLoginRecord findRecentRecordByMemberId(String memberId) {
		return memberLoginRecordDao.findRecentRecordByMemberId(memberId);
	}
}
