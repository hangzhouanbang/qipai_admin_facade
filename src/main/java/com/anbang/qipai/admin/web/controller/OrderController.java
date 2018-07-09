package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.OrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.OrderVO;
import com.highto.framework.web.page.ListPage;

/**
 * 充值记录controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/queryorder")
	public CommonVO queryOrder(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, OrderVO order) {
		CommonVO vo = new CommonVO();
		ListPage listPage = orderService.findOrderByConditions(page, size, order);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/download")
	public CommonVO downLoad(OrderVO order) {
		CommonVO vo = new CommonVO();
		String fileName = orderService.exportOrder(order);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData("/excel/" + fileName);
		return vo;
	}
}
