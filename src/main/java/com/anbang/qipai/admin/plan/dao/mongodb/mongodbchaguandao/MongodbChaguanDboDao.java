package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanDboDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanDboRepository;

@Component
public class MongodbChaguanDboDao implements ChaguanDboDao {

	@Autowired
	private ChaguanDboRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(ChaguanDbo dbo) {
		repository.save(dbo);
	}

	@Override
	public ChaguanDbo findByChaguanId(String chaguanId) {
		return repository.findOne(chaguanId);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public List<ChaguanDbo> find(int page, int size) {
		Query query = new Query();
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, ChaguanDbo.class);
	}

}
