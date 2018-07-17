package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentScoreClubCard;

public interface AgentScoreClubCardDao {
	void addScoreClubCard(AgentScoreClubCard card);

	boolean deleteScoreClubCard(String[] cardIds);

	boolean updateScoreById(String id, int score);

	boolean updateNumberById(String id, int number);

	List<AgentScoreClubCard> findScoreClubCard(int page, int size);

	long getAmount();
}
