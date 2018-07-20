package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import com.anbang.qipai.admin.plan.domain.members.MemberDbo;
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

	public void editMember(MemberDbo member) {
		memberDao.editMember(member);
	}
	
	public boolean updateMemberPhone(MemberDbo member) {
		return memberDao.updateMemberPhone(member);
	}
	
	public boolean resetMemberVip(MemberDbo member) {
		return memberDao.resetMemberVip(member);
	}
	
	public boolean updateMemberVip(MemberDbo member) {
		return memberDao.updateMemberVip(member);
	}

	public long countNewMemberByTime(long startTime, long endTime) {
		return memberDao.countNewMemberByTime(startTime, endTime);
	}

	public long countVIP() {
		return memberDao.countVIP();
	}

	public long countRemain(long deviation) {
		return memberDao.countRemain(deviation);
	}
}
