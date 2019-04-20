package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopOrder;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanShopOrderDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanShopOrderRepository;

@Component
public class MongodbChaguanShopOrderDao implements ChaguanShopOrderDao {

	@Autowired
	private ChaguanShopOrderRepository repository;

	@Override
	public void save(ChaguanShopOrder order) {
		repository.save(order);
	}

}
