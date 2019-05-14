package com.anbang.qipai.admin.msg.receiver.scoreshopreceiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anbang.qipai.admin.msg.channel.sink.scoreshop.ProductTypeSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.shop.GoodsType;
import com.anbang.qipai.admin.plan.service.shopservice.ShopManageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(ProductTypeSink.class)
public class ProductTypeMsgReceiver {
    @Autowired
    private ShopManageService shopManageService;

    Gson gson = new Gson();

    @StreamListener(ProductTypeSink.PRODUCT_TYPE)
    public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("add type".equals(msg) || "update type".equals(msg)) {
            JSONObject object = JSON.parseObject(json);

            GoodsType goodsType = new GoodsType();
            goodsType.setId(object.getString("id"));
            goodsType.setType(object.getString("desc"));
            shopManageService.saveGoodsType(goodsType);
        }

        if ("remove type".equals(msg)) {
            String[] ids = gson.fromJson(json, String[].class);
            shopManageService.deleteScoreShopProduct(ids[0]);
        }
    }
}
