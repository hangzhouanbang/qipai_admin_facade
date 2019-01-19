package com.anbang.qipai.admin.remote.service;

import com.anbang.qipai.admin.plan.bean.tasks.HongbaodianProduct;
import com.anbang.qipai.admin.plan.bean.tasks.WhiteList;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 红包远程服务
 */
@FeignClient("qipai-hongbao")
public interface QipaiHongBaoRemoteService {

    @RequestMapping(value = "/hongbaodianproduct/add_product")
    CommonRemoteVO add_product(@RequestBody HongbaodianProduct product);

    @RequestMapping(value = "/hongbaodianproduct/update_product")
    CommonRemoteVO update_product(@RequestBody HongbaodianProduct product);

    @RequestMapping(value = "/hongbaodianproduct/remove_product_by_id")
    CommonRemoteVO remove_product_by_id(@RequestBody String[] productIds);

    @RequestMapping(value = "/whitelist/add_whitelist")
    CommonRemoteVO addWhiteList(@RequestBody WhiteList whiteList);

    @RequestMapping(value = "/whitelist/remove_whitelist_by_id")
    CommonRemoteVO removeWhiteListById(@RequestBody String[] ids);
}
