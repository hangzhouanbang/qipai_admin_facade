package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanShopProductSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopProduct;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanShopService;
import com.google.gson.Gson;

@EnableBinding(ChaguanShopProductSink.class)
public class ChaguanShopProductMsgReceiver {
	@Autowired
	private ChaguanShopService chaguanShopService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanShopProductSink.CHAGUANSHOPPRODUCT)
	public void chaguanShopProduct(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("new product".equals(msg)) {
			ChaguanShopProduct product = gson.fromJson(json, ChaguanShopProduct.class);
			chaguanShopService.saveChaguanShopProduct(product);
		}
		if ("update product".equals(msg)) {
			ChaguanShopProduct product = gson.fromJson(json, ChaguanShopProduct.class);
			chaguanShopService.updateChaguanShopProduct(product);
		}
		if ("remove products".equals(msg)) {
			String[] productIds = gson.fromJson(json, String[].class);
			chaguanShopService.removeChaguanShopProduct(productIds);
		}
	}
}
