package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentScoreRecordDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreRecordDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentScoreRecordDboService {

	@Autowired
	private AgentScoreRecordDboDao agentScoreRecordDboDao;

	public void addAgentScoreRecordDbo(AgentScoreRecordDbo dbo) {
		agentScoreRecordDboDao.addAgentScoreRecordDbo(dbo);
	}

	public ListPage findAgentScoreRecordDboByConditions(int page, int size, AgentScoreRecordDbo dbo) {
		long amount = agentScoreRecordDboDao.getAmountByConditions(dbo);
		List<AgentScoreRecordDbo> recordList = agentScoreRecordDboDao.findAgentScoreRecordDboByConditions(page, size,
				dbo);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}
}
