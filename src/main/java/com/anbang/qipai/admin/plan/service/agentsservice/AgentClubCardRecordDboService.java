package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentClubCardRecordDboDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentClubCardRecordDboVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentClubCardRecordDboService {

	@Autowired
	private AgentClubCardRecordDboDao agentClubCardRecordDboDao;

	public void addAgentClubCardRecordDbo(AgentClubCardRecordDbo dbo) {
		agentClubCardRecordDboDao.addAgentClubCardRecordDbo(dbo);
	}

	public ListPage findAgentClubCardRecordDboByConditions(int page, int size, AgentClubCardRecordDboVO record) {
		long amount = agentClubCardRecordDboDao.getAmountByConditions(record);
		List<AgentClubCardRecordDbo> recordList = agentClubCardRecordDboDao.findAgentClubCardRecordDboByConditions(page,
				size, record);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}
}
