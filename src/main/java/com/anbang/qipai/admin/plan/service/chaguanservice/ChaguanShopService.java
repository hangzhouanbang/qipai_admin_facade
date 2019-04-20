package com.anbang.qipai.admin.plan.service.chaguanservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopOrder;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopProduct;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanShopOrderDao;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanShopProductDao;

@Service
public class ChaguanShopService {

	@Autowired
	private ChaguanShopOrderDao chaguanShopOrderDao;

	@Autowired
	private ChaguanShopProductDao chaguanShopProductDao;

	public void saveChaguanShopOrder(ChaguanShopOrder order) {
		chaguanShopOrderDao.save(order);
	}

	public void finishChaguanShopOrderDao(ChaguanShopOrder order) {
		chaguanShopOrderDao.save(order);
	}

	public void saveChaguanShopProduct(ChaguanShopProduct product) {
		chaguanShopProductDao.save(product);
	}

	public void updateChaguanShopProduct(ChaguanShopProduct product) {
		chaguanShopProductDao.save(product);
	}

	public void removeChaguanShopProduct(String[] productIds) {
		chaguanShopProductDao.removeByIds(productIds);
	}
}
