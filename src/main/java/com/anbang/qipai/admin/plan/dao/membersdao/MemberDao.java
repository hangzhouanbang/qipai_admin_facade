package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.members.MemberDbo;

public interface MemberDao {

	List<MemberDbo> findMemberDboByConditions(int page, int size, MemberDbo member);

	long getAmountByConditions(MemberDbo member);

	void addMember(MemberDbo member);

	void editMember(MemberDbo member);

	boolean updateMemberPhone(MemberDbo member);

	boolean resetMemberVip(MemberDbo member);
	
	boolean updateMemberVip(MemberDbo member);

	MemberDbo findMemberById(String memberId);

	long countNewMemberByTime(long startTime, long endTime);

	long countVIP();

	long countRemain(long deviation);
}
