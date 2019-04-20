package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanDboDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanDboRepository;

@Component
public class MongodbChaguanDboDao implements ChaguanDboDao {

	@Autowired
	private ChaguanDboRepository repository;

	@Override
	public void save(ChaguanDbo dbo) {
		repository.save(dbo);
	}

}
