package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanMemberDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanMemberDboDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanMemberDboRepository;

@Component
public class MongodbChaguanMemberDboDao implements ChaguanMemberDboDao {

	@Autowired
	private ChaguanMemberDboRepository repository;

	@Override
	public void save(ChaguanMemberDbo dbo) {
		repository.save(dbo);
	}

}
