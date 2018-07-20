package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentsdao.AgentDboDao;
import com.anbang.qipai.admin.plan.domain.agents.AgentDbo;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentDboService {

	@Autowired
	private AgentDboDao agentDboDao;

	public ListPage findAgentDboByConditions(int page, int size, AgentDboVO agent) {
		long amount = agentDboDao.getAmountByConditions(agent);
		List<AgentDbo> agentList = agentDboDao.findAgentDboByConditions(page, size, agent);
		ListPage listPage = new ListPage(agentList, page, size, (int) amount);
		return listPage;
	}

	public AgentDbo findAgentDboById(String agentId) {
		return agentDboDao.findAgentDboById(agentId);
	}

	public void addAgentDbo(AgentDbo agent) {
		agentDboDao.addAgentDbo(agent);
	}

	public boolean updateAgentDboBoss(String agentId, String bossId, String bossName) {
		return agentDboDao.updateAgentDboBoss(agentId, bossId, bossName);
	}

	public boolean removeAgentDboBoss(String agentId) {
		return agentDboDao.removeAgentDboBoss(agentId);
	}

	public boolean banAgentDboState(String agentId) {
		return agentDboDao.updateAgentDboState(agentId, "封禁");
	}

	public boolean liberateAgentDboState(String agentId) {
		return agentDboDao.updateAgentDboState(agentId, "正常");
	}

	public boolean updateAgentDboLevel(String agentId, int level) {
		return agentDboDao.updateAgentDboLevel(agentId, level);
	}

	public void updateAgnetInfoAndAgentAuth(AgentDbo agent) {
		agentDboDao.updateAgnetInfo(agent.getId(), agent.getPhone(), agent.getUserName(), agent.getIdCard(),
				agent.getFrontUrl(), agent.getReverseUrl());
		agentDboDao.updateAgentAuth(agent.getId(), agent.getAgentAuth());
	}
}
