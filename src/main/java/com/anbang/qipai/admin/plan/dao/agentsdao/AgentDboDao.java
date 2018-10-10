package com.anbang.qipai.admin.plan.dao.agentsdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.agents.AgentDbo;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;

public interface AgentDboDao {

	void addAgentDbo(AgentDbo agent);

	long getAmountByConditions(AgentDboVO agent);

	long countAmountByLevel(int level);

	List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDboVO agent);

	AgentDbo findAgentDboById(String agentId);

	void updateAgentDboBoss(String agentId, String bossId, String bossName);

	void updateAgentDboLevel(String agentId, int level);

	void updateAgentDboState(String agentId, String state);

	void updateAgnetInfo(String agentId, String phone, String userName, String area, String desc);

	void updateAgentDboInvitationCode(String agentId, String invitationCode);

	void updateAgentAuth(String agentId, boolean agentAuth);

	void removeAgentDboBoss(String agentId);

	void updateAgentCost(String agentId, double cost);
}
