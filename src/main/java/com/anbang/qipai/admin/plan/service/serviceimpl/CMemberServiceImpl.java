package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.anbang.qipai.admin.plan.dao.CMemberDao;
import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.CMemberService;

public class CMemberServiceImpl implements CMemberService {

	@Autowired
	private CMemberDao memberDao;

	@Override
	public List<Member> queryByConditionsAndPage(int page, int size, Member member) {
		return memberDao.queryByConditionsAndPage(page, size, member);
	}

}
