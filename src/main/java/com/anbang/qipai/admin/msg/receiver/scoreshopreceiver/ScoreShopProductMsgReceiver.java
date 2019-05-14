package com.anbang.qipai.admin.msg.receiver.scoreshopreceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.channel.sink.scoreshop.ScoreShopProductSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.shop.ScoreShopProductDbo;
import com.anbang.qipai.admin.plan.dao.shopdao.ScoreShopProductDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(ScoreShopProductSink.class)
public class ScoreShopProductMsgReceiver {

    @Autowired
    private ScoreShopProductDao scoreShopProductDao;

    Gson gson = new Gson();

    @StreamListener(ScoreShopProductSink.SCORE_SHOP_PRODUCT_DBO)
    public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("update".equals(msg)) {
            ScoreShopProductDbo productDbo = JSON.parseObject(json, ScoreShopProductDbo.class);
            scoreShopProductDao.save(productDbo);
        }
    }
}
