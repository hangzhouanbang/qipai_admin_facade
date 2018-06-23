package com.anbang.qipai.admin.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = memberDao.getAmount(member);
		List<MemberDbo> memberList = memberDao.queryByConditionsAndPage(page, size, sort, member);
		ListPage listPage = new ListPage(memberList, page, size, (int) amount);
		return listPage;
	}

	public void addMember(MemberDbo member) {
		memberDao.addMember(member);
	}

	public Boolean deleteMember(String[] ids) {
		return memberDao.deleteMember(ids);
	}

	public void editMember(MemberDbo member) {
		memberDao.editMember(member);
	}

}
