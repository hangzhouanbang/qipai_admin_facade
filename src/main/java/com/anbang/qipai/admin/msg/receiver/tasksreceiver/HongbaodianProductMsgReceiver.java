package com.anbang.qipai.admin.msg.receiver.tasksreceiver;

import com.anbang.qipai.admin.msg.channel.sink.HongbaodianProductSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.tasks.HongbaodianProduct;
import com.anbang.qipai.admin.plan.service.tasksservice.ExchangeManageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author yins
 * @Description:
 */
@EnableBinding(HongbaodianProductSink.class)
public class HongbaodianProductMsgReceiver {

    @Autowired
    private ExchangeManageService exchangeManageService;

    private Gson gson = new Gson();

    @StreamListener("hongbaodianProduct")
    public void activity(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("add product".equals(msg)) {
            HongbaodianProduct hongbaodianProduct = gson.fromJson(json, HongbaodianProduct.class);
            exchangeManageService.addExchangeManage(hongbaodianProduct);
        }
        if ("update product".equals(msg)) {
            HongbaodianProduct hongbaodianProduct = gson.fromJson(json, HongbaodianProduct.class);
            exchangeManageService.updateExchangeManage(hongbaodianProduct);
        }
        if ("remove product".equals(msg)) {
            String[] ids = gson.fromJson(json, String[].class);
            exchangeManageService.deleteExchangeManage(ids);
        }
    }
}
