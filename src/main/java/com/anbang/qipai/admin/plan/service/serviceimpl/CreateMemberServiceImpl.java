package com.anbang.qipai.admin.plan.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.CreateMemberDao;
import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;
import com.anbang.qipai.admin.plan.service.CreateMemberService;

@Service
public class CreateMemberServiceImpl implements CreateMemberService{

	@Autowired
	private CreateMemberDao memberDao;
	
	@Override
	public CreateMemberConfiguration save(CreateMemberConfiguration member) {
		return memberDao.save(member);
	}
	
}
