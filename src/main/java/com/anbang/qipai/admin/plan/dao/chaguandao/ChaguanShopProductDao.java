package com.anbang.qipai.admin.plan.dao.chaguandao;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopProduct;

public interface ChaguanShopProductDao {

	void save(ChaguanShopProduct product);

	void removeByIds(String[] productIds);
}
