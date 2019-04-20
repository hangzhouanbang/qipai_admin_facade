package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanShopProduct;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-chaguan")
public interface QipaiChaguanRemoteService {

	@RequestMapping("/shop/product_add")
	public CommonRemoteVO shop_product_add(@RequestBody ChaguanShopProduct product);

	@RequestMapping("/shop/product_update")
	public CommonRemoteVO shop_product_update(@RequestBody ChaguanShopProduct product);

	@RequestMapping("/shop/product_remove")
	public CommonRemoteVO shop_product_remove(@RequestBody String[] productIds);

	@RequestMapping("/agentchaguan/apply_pass")
	public CommonRemoteVO agentchaguan_apply_pass(@RequestParam(value = "applyId") String applyId);

	@RequestMapping("/agentchaguan/apply_refuse")
	public CommonRemoteVO agentchaguan_apply_refuse(@RequestParam(value = "applyId") String applyId);
}
