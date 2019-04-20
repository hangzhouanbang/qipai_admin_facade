package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanYushiRecordDboDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanYushiRecordDboRepository;

@Component
public class MongodbChaguanYushiRecordDboDao implements ChaguanYushiRecordDboDao {

	@Autowired
	private ChaguanYushiRecordDboRepository repository;

	@Override
	public void save(ChaguanYushiRecordDbo dbo) {
		repository.save(dbo);
	}

}
