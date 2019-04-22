package com.anbang.qipai.admin.msg.receiver.chaguanreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.chaguan.ChaguanShopOrderSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopOrder;
import com.anbang.qipai.admin.plan.service.chaguanservice.ChaguanShopService;
import com.google.gson.Gson;

@EnableBinding(ChaguanShopOrderSink.class)
public class ChaguanShopOrderMsgReceiver {
	@Autowired
	private ChaguanShopService chaguanShopService;

	private Gson gson = new Gson();

	@StreamListener(ChaguanShopOrderSink.CHAGUANSHOPORDER)
	public void chaguanShopOrder(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		ChaguanShopOrder order = gson.fromJson(json, ChaguanShopOrder.class);
		if ("new order".equals(msg)) {
			chaguanShopService.saveChaguanShopOrder(order);
		}
		if ("finish order".equals(msg)) {
			chaguanShopService.finishChaguanShopOrder(order);
		}
	}
}
