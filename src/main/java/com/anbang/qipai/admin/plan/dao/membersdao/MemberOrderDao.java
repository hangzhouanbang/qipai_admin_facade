package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOrderVO;

public interface MemberOrderDao {

	List<MemberOrder> findOrderByConditions(int page, int size, MemberOrderVO order);

	long getAmountByConditions(MemberOrderVO order);

	void addOrder(MemberOrder order);

	double countCostByTime(long startTime, long endTime);

	void orderFinished(String id, String transaction_id, String status, long deliveTime);
}
