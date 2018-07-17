package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentClubCardRecordDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentClubCardRecordDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentClubCardRecordDboService {

	@Autowired
	private AgentClubCardRecordDboDao agentClubCardRecordDboDao;

	public void addAgentClubCardRecordDbo(AgentClubCardRecordDbo dbo) {
		agentClubCardRecordDboDao.addAgentClubCardRecordDbo(dbo);
	}

	public ListPage findAgentClubCardRecordDboByConditions(int page, int size, AgentClubCardRecordDbo dbo) {
		long amount = agentClubCardRecordDboDao.getAmountByConditions(dbo);
		List<AgentClubCardRecordDbo> recordList = agentClubCardRecordDboDao.findAgentClubCardRecordDboByConditions(page,
				size, dbo);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}
}
