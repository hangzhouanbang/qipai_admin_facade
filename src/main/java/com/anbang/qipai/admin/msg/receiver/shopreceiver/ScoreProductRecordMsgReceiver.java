package com.anbang.qipai.admin.msg.receiver.shopreceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.channel.sink.shop.ScoreProductRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.shop.ScoreProductRecord;
import com.anbang.qipai.admin.plan.dao.shopdao.ScoreProductRecordDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(ScoreProductRecordSink.class)
public class ScoreProductRecordMsgReceiver {
    @Autowired
    private ScoreProductRecordDao scoreProductRecordDao;

    Gson gson = new Gson();

    @StreamListener(ScoreProductRecordSink.SCOREPRODUCT_RECORD)
    public void recordMemberXiuxianchangGoldRecordDbo(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("add record".equals(msg) || "finish record".equals(msg) ) {
            ScoreProductRecord record = JSON.parseObject(json, ScoreProductRecord.class);
            scoreProductRecordDao.save(record);
        }
    }
}
