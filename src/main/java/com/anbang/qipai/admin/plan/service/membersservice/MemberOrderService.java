package com.anbang.qipai.admin.plan.service.membersservice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.membersdao.MemberOrderDao;
import com.anbang.qipai.admin.plan.domain.members.MemberOrder;
import com.anbang.qipai.admin.util.ExcelUtils;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOrderVO;
import com.highto.framework.web.page.ListPage;

@Service
public class MemberOrderService {

	@Autowired
	private MemberOrderDao orderDao;

	public void addOrder(MemberOrder order) {
		orderDao.addOrder(order);
	}

	public ListPage findOrderByConditions(int page, int size, MemberOrderVO order) {
		long amount = orderDao.getAmountByConditions(order);
		List<MemberOrder> orderList = orderDao.findOrderByConditions(page, size, order);
		ListPage listPage = new ListPage(orderList, page, size, (int) amount);
		return listPage;
	}

	public boolean updateOrderStatusAndDeliveTime(MemberOrder order) {
		return orderDao.updateOrderStatusAndDeliveTime(order);
	}

	public double countCostByTime(long startTime, long endTime) {
		return orderDao.countCostByTime(startTime, endTime);
	}

	public String exportOrder(MemberOrderVO order) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = format.format(date) + "order.xls";
		long amount = orderDao.getAmountByConditions(order);
		int size = 300;
		long pageNum = amount % size > 0 ? amount / size + 1 : amount / size;
		for (int page = 1; page <= pageNum; page++) {
			List<MemberOrder> orderList = orderDao.findOrderByConditions(page, size, order);
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