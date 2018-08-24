package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;

public interface MemberDao {

	List<MemberDbo> findMemberDboByConditions(int page, int size, MemberDbo member);

	long getAmountByConditions(MemberDbo member);

	void addMember(MemberDbo member);

	void updateMemberPhone(MemberDbo member);

	void resetMemberVip(MemberDbo member);

	void updateMemberVip(MemberDbo member);

	void updateMemberLogin(String memberId, String state, String loginIp, boolean vip, long lastLoginTime);

	MemberDbo findMemberById(String memberId);

	long countNewMemberByTime(long startTime, long endTime);

	long countVIP();

	long countRemain(long deviation);

	void verifyUser(String memberId, String realName, String IDcard, boolean verify);
}
