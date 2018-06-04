package com.anbang.qipai.admin.plan.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.CreateMemberDao;
import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
import com.anbang.qipai.admin.plan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private CreateMemberDao memberDao;
	
	@Override
	public CreateMemberConfiguration save(Integer goldForNewMember) {
		CreateMemberConfiguration member=new CreateMemberConfiguration();
		member.setId("1");
		member.setGoldForNewMember(goldForNewMember);
		return memberDao.save(member);
	}
	
}
