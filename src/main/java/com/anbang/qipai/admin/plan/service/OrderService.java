package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.recorddao.OrderDao;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.web.vo.OrderVO;
import com.highto.framework.web.page.ListPage;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	public ListPage findOrder(int page, int size, Sort sort, OrderVO order) {
		long amount = orderDao.getAmount(order);
		List<Order> orderList = orderDao.findOrder(page, size, sort, order);
		ListPage listPage = new ListPage(orderList, page, size, (int) amount);
		return listPage;
	}

	public Boolean updateOrder(Order order) {
		Order rcd = orderDao.findOrderByOut_trade_no(order.getOut_trade_no());
		if (order.getStatus() == 0) {
			return orderDao.updateOrder(rcd.getOut_trade_no(), order);
		}
		return false;
	}
}
