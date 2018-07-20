package com.anbang.qipai.admin.plan.dao.agentsdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agents.AgentDbo;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;

public interface AgentDboDao {

	void addAgentDbo(AgentDbo agent);

	long getAmountByConditions(AgentDboVO agent);

	List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDboVO agent);

	AgentDbo findAgentDboById(String agentId);

	boolean updateAgentDboBoss(String agentId, String bossId, String bossName);

	boolean updateAgentDboLevel(String agentId, int level);

	boolean updateAgentDboState(String agentId, String state);

	boolean updateAgnetInfo(String agentId, String phone, String userName, String idCard, String frontUrl,
			String reverseUrl);

	boolean updateAgentAuth(String agentId, boolean agentAuth);

	boolean removeAgentDboBoss(String agentId);
}
