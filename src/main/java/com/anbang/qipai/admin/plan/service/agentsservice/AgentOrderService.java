package com.anbang.qipai.admin.plan.service.agentsservice;

import com.anbang.qipai.admin.web.query.AgentOrderQuery;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentPayType;
import com.highto.framework.web.page.ListPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentOrder;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentOrderDao;

import java.util.List;

@Service
public class AgentOrderService {

	@Autowired
	private AgentOrderDao orderDao;

	public void addOrder(AgentOrder order) {
		orderDao.addOrder(order);
	}
	
	public void orderFinished(String id, String transaction_id, String status, long deliveTime) {
		orderDao.orderFinished(id, transaction_id, status, deliveTime);
	}

	public double findAmountByBean(AgentOrderQuery agentOrderQuery){
		return orderDao.findAmountByBean(agentOrderQuery);
	}

	public ListPage findAgentOrderByBean(int page, int size, AgentOrderQuery agentOrderQuery) {
		long count = orderDao.countByBean(agentOrderQuery);
		List<AgentOrder> agentOrders = orderDao.findAgentOrderByBean(page,size,agentOrderQuery);
		for(AgentOrder list : agentOrders) {
			list.setPay_type(AgentPayType.getSummaryText(list.getPay_type()));
			list.setProductName(conversion(list.getProductName(),list.getNumber()));
		}
		ListPage listPage = new ListPage(agentOrders, page, size, (int)count);
		return listPage;
	}

	//显示转换
	public static String conversion(String productName, int number) {
		if (StringUtils.contains(productName,"卡")) {
			return productName + number + "张";
		}
		if ("玉石".equals(productName)) {
			return productName + number + "个";
		}
		return productName;
	}
}
