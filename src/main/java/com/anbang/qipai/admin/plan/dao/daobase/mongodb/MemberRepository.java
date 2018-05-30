package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;


public interface MemberRepository extends MongoRepository<CreateMemberConfiguration, String>{

}
