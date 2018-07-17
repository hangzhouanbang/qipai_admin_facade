package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentCostClubCard;

public interface AgentCostClubCardDao {
	void addCostClubCard(AgentCostClubCard card);

	boolean deleteCostClubCard(String[] cardIds);

	boolean updateCostById(String id, int cost);

	boolean updateNumberById(String id, int number);

	List<AgentCostClubCard> findCostClubCard(int page, int size);

	long getAmount();
}
