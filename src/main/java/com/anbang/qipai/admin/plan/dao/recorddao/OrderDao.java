package com.anbang.qipai.admin.plan.dao.recorddao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.web.vo.OrderVO;

public interface OrderDao {

	List<Order> findOrder(int page, int size, Sort sort, OrderVO order);
	
	long getAmount(OrderVO order);

	Order findOrderByOut_trade_no(String out_trade_no);

	void addOrder(Order order);

	Boolean updateOrderStatus(String out_trade_no, int status);

	Boolean updateTransaction_id(String out_trade_no, String transaction_id);
}
