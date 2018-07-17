package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentClubCardRecordDbo;

public interface AgentClubCardRecordDboDao {
	void addAgentClubCardRecordDbo(AgentClubCardRecordDbo dbo);

	long getAmountByConditions(AgentClubCardRecordDbo dbo);

	List<AgentClubCardRecordDbo> findAgentClubCardRecordDboByConditions(int page, int size, AgentClubCardRecordDbo dbo);
}
