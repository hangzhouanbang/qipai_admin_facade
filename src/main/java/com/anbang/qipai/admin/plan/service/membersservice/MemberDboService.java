package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberDboService {

	@Autowired
	private MemberDao memberDao;

	public ListPage findMemberDboByConditions(int page, int size, MemberDbo member) {
		long amount = memberDao.getAmountByConditions(member);
		List<MemberDbo> memberList = memberDao.findMemberDboByConditions(page, size, member);
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public void addMember(MemberDbo member) {
		memberDao.addMember(member);
	}

	public MemberDbo findMemberById(String memberId) {
		return memberDao.findMemberById(memberId);
	}

	public void updateMemberPhone(MemberDbo member) {
		memberDao.updateMemberPhone(member.getId(), member.getPhone());
	}

	public void updateMemberVip(MemberDbo member) {
		memberDao.updateMemberVip(member.getId(), member.isVip());
	}

	public void memberOrderDelive(MemberDbo member) {
		memberDao.memberOrderDelive(member.getId(), member.isVip(), member.getVipEndTime(), member.getVipLevel(),
				member.getVipScore());
	}

	public void rechargeVip(MemberDbo member) {
		memberDao.rechargeVip(member.getId(), member.isVip(), member.getVipEndTime());
	}

	public long countNewMemberByTime(long startTime, long endTime) {
		return memberDao.countNewMemberByTime(startTime, endTime);
	}

	public long countVipMember() {
		return memberDao.countVipMember();
	}

	public long countRemain(long deviation) {
		return memberDao.countRemain(deviation);
	}

	public void updateMemberRealUser(MemberDbo member) {
		memberDao.updateMemberRealUser(member.getId(), member.getRealName(), member.getIDcard(), member.isVerifyUser());
	}

	public void updateMemberOnlineState(MemberDbo member) {
		memberDao.updateMemberOnlineState(member.getId(), member.getOnlineState());
	}
}
