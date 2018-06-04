package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.domain.Member;
import com.anbang.qipai.admin.plan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public List<Member> queryByConditionsAndPage(int page, int size, Member member) {
		return memberDao.queryByConditionsAndPage(page, size, member);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean deleteMember(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
