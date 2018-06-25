package com.anbang.qipai.admin.plan.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.anbang.qipai.admin.plan.dao.MemberDao;
import com.anbang.qipai.admin.plan.dao.recorddao.OrderDao;

public class ReportService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
}
