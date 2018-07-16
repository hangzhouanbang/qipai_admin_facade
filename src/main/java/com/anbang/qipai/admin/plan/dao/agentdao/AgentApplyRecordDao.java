package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;

public interface AgentApplyRecordDao {

	void addAgentApplyRecord(AgentApplyRecord record);

	boolean updateAgentApplyRecordSate(String recordId, String state);

	long getAmountByTime(long startTime, long endTime);

	List<AgentApplyRecord> findAgentApplyRecordByTime(long startTime, long endTime);

	AgentApplyRecord findAgentApplyRecordById(String recordId);
}
