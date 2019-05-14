package com.anbang.qipai.admin.msg.receiver.shopreceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.channel.sink.shop.HongbaodianProductRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.shop.HongbaodianProductRecord;
import com.anbang.qipai.admin.plan.dao.shopdao.HongbaodianProductRecordDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(HongbaodianProductRecordSink.class)
public class HongbaodianProductRecordMsgReceiver {

    @Autowired
    private HongbaodianProductRecordDao hongbaodianProductRecordDao;

    Gson gson = new Gson();

    @StreamListener(HongbaodianProductRecordSink.HONGBAODIAN_PRODUCT_RECORD)
    public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("add record".equals(msg) || "finish record".equals(msg)) {
            HongbaodianProductRecord record = JSON.parseObject(json, HongbaodianProductRecord.class);
            hongbaodianProductRecordDao.save(record);
        }
    }
}
