package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;

/**连接设置金币表
 * @author 程佳 2018.5.31
 * **/
public interface MemberRepository extends MongoRepository<CreateMemberConfiguration, String>{

}
