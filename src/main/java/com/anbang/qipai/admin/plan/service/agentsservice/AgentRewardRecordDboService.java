package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentRewardRecordDbo;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentRewardRecordDboDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentRewardRecordDboVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentRewardRecordDboService {

	@Autowired
	private AgentRewardRecordDboDao agentRewardRecordDboDao;

	public void addAgentRewardRecordDbo(AgentRewardRecordDbo record) {
		agentRewardRecordDboDao.addAgentRewardRecordDbo(record);
	}

	public ListPage findAgentRewardRecordDboByConditions(int page, int size, AgentRewardRecordDboVO record) {
		long amount = agentRewardRecordDboDao.getAmountByConditions(record);
		List<AgentRewardRecordDbo> recordList = agentRewardRecordDboDao.findAgentRewardRecordDboByConditions(page, size,
				record);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}
}
