package com.anbang.qipai.admin.msg.receiver.shopreceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.channel.sink.shop.HongbaodianShopProductDboSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.shop.HongbaodianShopProductDbo;
import com.anbang.qipai.admin.plan.dao.shopdao.HongbaodianShopProductDboDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(HongbaodianShopProductDboSink.class)
public class HongbaodianShopProductDboMsgreceiver {
    @Autowired
    private HongbaodianShopProductDboDao hongbaodianShopProductDboDao;

    Gson gson = new Gson();

    @StreamListener(HongbaodianShopProductDboSink.HONGBAODIAN_SHOP_PRODUCT_DBO)
    public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("update".equals(msg)) {
            HongbaodianShopProductDbo record = JSON.parseObject(json, HongbaodianShopProductDbo.class);
            hongbaodianShopProductDboDao.save(record);
        }
    }
}
