package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.config.AgentApplyState;
import com.anbang.qipai.admin.plan.dao.agentdao.AgentApplyRecordDao;
import com.anbang.qipai.admin.plan.dao.agentdao.AgentDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentDboService {

	@Autowired
	private AgentDboDao agentDboDao;

	@Autowired
	private AgentApplyRecordDao agentApplyRecordDao;

	public ListPage findAgentDboByConditions(int page, int size, AgentDbo agent) {
		long amount = agentDboDao.getAmountByConditions(agent);
		List<AgentDbo> agentList = agentDboDao.findAgentDboByConditions(page, size, agent);
		ListPage listPage = new ListPage(agentList, page, size, (int) amount);
		return listPage;
	}

	public void addAgentDbo(AgentDbo agent) {
		agentDboDao.addAgentDbo(agent);
	}

	public boolean updateAgentDboBossId(String agentId, String bossId) {
		return agentDboDao.updateAgentDboBossId(agentId, bossId);
	}

	public boolean updateAgentDboLevel(String agentId, int level) {
		return agentDboDao.updateAgentDboLevel(agentId, level);
	}

	public void updateAgnetInfo(AgentApplyRecord record) {
		agentDboDao.updateAgnetInfo(record.getAgentId(), record.getPhone(), record.getUserName(), record.getIdCard(),
				record.getFrontUrl(), record.getReverseUrl());
		agentDboDao.updateAgentAuth(record.getAgentId(), true);
		agentApplyRecordDao.updateAgentApplyRecordSate(record.getId(), AgentApplyState.APPLYSUCCESS);
	}
}
