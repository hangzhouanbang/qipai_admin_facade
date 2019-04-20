package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanApply;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanApplyDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanApplyReppository;

@Component
public class MongodbChaguanApplyDao implements ChaguanApplyDao {

	@Autowired
	private ChaguanApplyReppository repository;

	@Override
	public void save(ChaguanApply apply) {
		repository.save(apply);
	}

}
