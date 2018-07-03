package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	public ListPage queryByConditionsAndPage(int page, int size, Sort sort, MemberDbo member) {
		long amount = memberDao.getAmount(member);
		List<MemberDbo> memberList = memberDao.queryByConditionsAndPage(page, size, sort, member);
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public void addMember(MemberDbo member) {
		memberDao.addMember(member);
	}
	
	public MemberDbo findMemberDbo(String id) {
		return memberDao.findMemberDbo(id);
	}

	public Boolean deleteMember(String[] ids) {
		return memberDao.deleteMember(ids);
	}

	public void editMember(MemberDbo member) {
		memberDao.editMember(member);
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
