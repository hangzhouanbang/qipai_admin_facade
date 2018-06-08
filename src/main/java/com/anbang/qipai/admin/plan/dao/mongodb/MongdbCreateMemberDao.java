package com.anbang.qipai.admin.plan.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.creatememberdao.CreateMemberDao;
import com.anbang.qipai.admin.plan.dao.mongodb.repository.CreateMemberRepository;
import com.anbang.qipai.admin.plan.domain.createmember.CreateMemberConfiguration;

@Component
public class MongdbCreateMemberDao implements CreateMemberDao{
	
	@Autowired
	private CreateMemberRepository memberRepository;

	@Override
	public CreateMemberConfiguration save(CreateMemberConfiguration member) {
		return memberRepository.save(member);
	}

}
