package com.anbang.qipai.admin.plan.dao.membersdao;

import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;

public interface MemberLoginRecordDao {

	void save(MemberLoginRecord record);

	void updateOnlineTimeById(String id, long onlineTime);

	int countLoginMemberByTime(long startTime, long endTime);

	int countRemainMemberByDeviationTime(long deviation);

	MemberLoginRecord findRecentRecordByMemberId(String memberId);
}
