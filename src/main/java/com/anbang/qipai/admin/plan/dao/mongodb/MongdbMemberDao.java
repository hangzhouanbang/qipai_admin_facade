package com.anbang.qipai.admin.plan.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.dao.mongodb.repository.MemberRepository;
import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;

@Component
public class MongdbMemberDao implements MemberDao{
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public CreateMemberConfiguration save(CreateMemberConfiguration member) {
		return memberRepository.save(member);
	}

}
