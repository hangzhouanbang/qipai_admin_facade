package com.anbang.qipai.admin.plan.dao.recorddao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.web.vo.OrderVO;

public interface OrderDao {

	List<Order> findOrderByConditions(int page, int size, OrderVO order);

	long getAmountByConditions(OrderVO order);

	void addOrder(Order order);

	double countCostByTime(long startTime, long endTime);

	boolean updateOrderStatusAndDeliveTime(Order order);
}
