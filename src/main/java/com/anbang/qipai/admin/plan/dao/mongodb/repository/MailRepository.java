package com.anbang.qipai.admin.plan.dao.mongodb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.mail.SystemMail;

public interface MailRepository extends MongoRepository<SystemMail,String>{
	

}
