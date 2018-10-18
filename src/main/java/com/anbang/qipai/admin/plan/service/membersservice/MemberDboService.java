package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOnlineState;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberDboService {

	@Autowired
	private MemberDao memberDao;

	public ListPage findMemberDboByConditions(int page, int size, MemberVO member) {
		long amount = memberDao.getAmountByConditions(member);
		List<MemberDbo> memberList = memberDao.findMemberDboByConditions(page, size, member);
		for (int i = 0; i < memberList.size(); i++) {
			String onlineState = memberList.get(i).getOnlineState();
			memberList.get(i).setOnlineState(MemberOnlineState.getSummaryText(onlineState));
		}
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public long countAmount() {
		return memberDao.countAmount();
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

	public void updateMemberCost(MemberDbo member) {
		memberDao.updateMemberCost(member.getId(), member.getCost());
	}

	public void updateMemberGold(String memberId, int gold) {
		memberDao.updateMemberGold(memberId, gold);
	}

	public void updateMemberScore(String memberId, int score) {
		memberDao.updateMemberScore(memberId, score);
	}

	public void memberOrderDelive(MemberDbo member) {
		memberDao.memberOrderDelive(member.getId(), member.isVip(), member.getVipEndTime(), member.getVipLevel(),
				member.getVipScore(), member.getCost());
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

	public void updateMemberRealUser(MemberDbo member) {
		memberDao.updateMemberRealUser(member.getId(), member.getRealName(), member.getIDcard(), member.isVerifyUser());
	}

	public void updateMemberBindAgent(String memberId, boolean bindAgent) {
		memberDao.updateMemberBindAgent(memberId, bindAgent);
	}

	public void updateMemberOnlineState(String memberId, String onlineState) {
		memberDao.updateMemberOnlineState(memberId, onlineState);
	}

	public List<String> findVipMemberId() {
		List<String> ids = memberDao.findVipMemberId();
		return ids;
	}

	public List<String> findMemberId() {
		List<String> ids = memberDao.findMemberId();
		return ids;
	}

	public List<String> findAllMemberId() {
		List<String> ids = memberDao.findAllMemberId();
		return ids;
	}
}
