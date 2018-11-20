package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;

public interface MemberDao {

	List<MemberDbo> findMemberDboByIds(String[] memberIds);

	List<MemberDbo> findMemberDboByConditions(int page, int size, MemberVO member);

	long getAmountByConditions(MemberVO member);

	long countAmount();

	void addMember(MemberDbo member);

	void updateMemberPhone(String memberId, String phone);

	void updateMemberVip(String memberId, boolean vip);

	void updateMemberCost(String memberId, double cost);

	void updateMemberGold(String memberId, int gold);

	void updateMemberScore(String memberId, int score);

	void memberOrderDelive(String memberId, boolean vip, long vipEndTime, int vipLevel, double vipScore, double cost);

	void rechargeVip(String memberId, boolean vip, long vipEndTime);

	MemberDbo findMemberById(String memberId);

	long countNewMemberByTime(long startTime, long endTime);

	long countVipMember();

	void updateMemberRealUser(String memberId, String realName, String IDcard, boolean verify);

	void updateMemberBindAgent(String memberId, String agentId, boolean bindAgent);

	void removeMemberBindAgent(String memberId);

	void updateMemberOnlineState(String memberId, String onlineState);

	List<String> findAllMemberId();

	List<String> findMemberId();

	List<String> findVipMemberId();

}
