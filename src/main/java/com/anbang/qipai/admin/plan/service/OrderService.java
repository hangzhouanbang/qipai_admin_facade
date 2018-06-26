package com.anbang.qipai.admin.plan.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.recorddao.OrderDao;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.util.ExcelUtils;
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
		return orderDao.updateOrder(order);
	}

	public double countCostByTime(long startTime, long endTime) {
		return orderDao.countCostByTime(startTime, endTime);
	}

	public String exportOrder(Sort sort, OrderVO order) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xls";
		long amount = orderDao.getAmount(order);
		int size = 300;
		long pageNum = amount % size > 0 ? amount / size + 1 : amount / size;
		for (int page = 1; page <= pageNum; page++) {
			List<Order> orderList = orderDao.findOrder(page, size, sort, order);
			try {
				ExcelUtils.generateOrderExcel(orderList, fileName);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		ExcelUtils.rowid = 0;
		ExcelUtils.sheetNum = 1;
		return fileName;
	}
}
