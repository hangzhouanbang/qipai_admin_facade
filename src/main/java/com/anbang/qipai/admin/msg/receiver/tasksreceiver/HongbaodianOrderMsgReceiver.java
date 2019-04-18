package com.anbang.qipai.admin.msg.receiver.tasksreceiver;

import com.anbang.qipai.admin.msg.channel.sink.HongbaodianOrderSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.msjobj.dbo.HongbaodianOrder;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.tasks.ExchangeRecord;
import com.anbang.qipai.admin.plan.bean.tasks.RewardType;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.tasksservice.ExchangeRecordService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author yins
 * @Description: 红包兑换记录消息消费
 */
@EnableBinding(HongbaodianOrderSink.class)
public class HongbaodianOrderMsgReceiver {

    @Autowired
    private ExchangeRecordService exchangeRecordService;

    private Gson gson = new Gson();

    @StreamListener("hongbaodianOrder")
    public void hongBao(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        HongbaodianOrder dbo = gson.fromJson(json, HongbaodianOrder.class);
        ExchangeRecord exchangeRecord = new ExchangeRecord();
        exchangeRecord.setId(dbo.getId());
        exchangeRecord.setType(dbo.getRewardType().toString());
        exchangeRecord.setNickName(dbo.getReceiverName());
        exchangeRecord.setPlayerId(dbo.getReceiverId());
        exchangeRecord.setLoginIP(dbo.getReqIP());
        exchangeRecord.setExchangeTime(dbo.getCreateTime());
        exchangeRecord.setExchangeType(RewardType.toMap().get(dbo.getRewardType().name()));
        exchangeRecord.setExchangeAmount((int)dbo.getRewardNum());
        exchangeRecord.setItemName(dbo.getProduceName());
        if (StringUtils.isNotBlank(dbo.getProvince()) || StringUtils.isNotBlank(dbo.getCity())) {
            exchangeRecord.setIpAddress(dbo.getProvince() + dbo.getCity());
        }
        exchangeRecord.setStatus(dbo.getStatus());
        if ("add order".equals(msg)) {
            exchangeRecordService.addExchangeRecord(exchangeRecord);
        }
        if ("finish order".equals(msg)) {
            exchangeRecordService.updateExchangeRecord(exchangeRecord);
        }
    }
}
