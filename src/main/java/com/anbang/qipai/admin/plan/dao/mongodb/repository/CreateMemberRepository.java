package com.anbang.qipai.admin.plan.dao.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.CreateMemberConfiguration;

/**连接设置金币表
 * @author 程佳 2018.5.31
 * **/
public interface CreateMemberRepository extends MongoRepository<CreateMemberConfiguration, String>{

}
