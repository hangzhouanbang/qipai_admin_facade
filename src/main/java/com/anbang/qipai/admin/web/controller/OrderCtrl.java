package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.OrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.OrderVO;
import com.highto.framework.web.page.ListPage;

@RestController
@RequestMapping("/order")
public class OrderCtrl {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/queryorder")
	public CommonVO queryOrder(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, OrderVO order) {
		CommonVO vo = new CommonVO();
		System.out.println(order);
		Sort sort = new Sort(new Order("id"));
		ListPage listPage = orderService.findOrder(page.intValue(), size.intValue(), sort, order);
		vo.setSuccess(true);
		vo.setMsg("orderList");
		vo.setData(listPage);
		return vo;
	}
}
