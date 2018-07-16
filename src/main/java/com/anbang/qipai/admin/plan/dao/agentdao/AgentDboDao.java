package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;

public interface AgentDboDao {

	void addAgentDbo(AgentDbo agent);

	long getAmountByConditions(AgentDbo agent);

	List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDbo agent);

	boolean updateAgentDboBossId(String agentId, String bossId);

	boolean updateAgentDboLevel(String agentId, int level);

	boolean updateAgnetInfo(String agentId, String phone, String userName, String idCard, String frontUrl,
			String reverseUrl);
	
	boolean updateAgentAuth(String agentId, boolean agentAuth);
}
