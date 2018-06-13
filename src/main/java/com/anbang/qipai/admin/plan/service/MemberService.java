package com.anbang.qipai.admin.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = memberDao.getAmount();
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		List<Member> memberList = memberDao.queryByConditionsAndPage(page, size, sort, nickname);
		map.put("pageNumber", pageNumber);
		map.put("memberList", memberList);
		return map;
	}

	public void addMember(Member member) {
		memberDao.addMember(member);
	}

	public Boolean deleteMember(String[] ids) {
		return memberDao.deleteMember(ids);
	}

	public Boolean editMember(Member member) {
		return memberDao.editMember(member);
	}

}
