package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentScoreRecordDbo;

public interface AgentScoreRecordDboDao {

	void addAgentScoreRecordDbo(AgentScoreRecordDbo dbo);

	long getAmountByConditions(AgentScoreRecordDbo dbo);

	List<AgentScoreRecordDbo> findAgentScoreRecordDboByConditions(int page, int size, AgentScoreRecordDbo dbo);
}
