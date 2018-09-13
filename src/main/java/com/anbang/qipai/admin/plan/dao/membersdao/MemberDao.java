package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;

public interface MemberDao {

	List<MemberDbo> findMemberDboByConditions(int page, int size, MemberDbo member, int queryType);

	long getAmountByConditions(MemberDbo member, int queryType);

	void addMember(MemberDbo member);

	void updateMemberPhone(String memberId, String phone);

	void updateMemberVip(String memberId, boolean vip);

	void memberOrderDelive(String memberId, boolean vip, long vipEndTime, int vipLevel, double vipScore);

	void rechargeVip(String memberId, boolean vip, long vipEndTime);

	MemberDbo findMemberById(String memberId);

	long countNewMemberByTime(long startTime, long endTime);

	long countVipMember();

	void updateMemberRealUser(String memberId, String realName, String IDcard, boolean verify);

	void updateMemberOnlineState(String memberId, String onlineState);

	List<String> findAllMemberId();

	List<String> findMemberId();

	List<String> findVipMemberId();
}
