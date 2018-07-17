package com.anbang.qipai.admin.plan.dao.agentdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;

public interface AgentApplyRecordDao {

	void addAgentApplyRecord(AgentApplyRecord record);

	boolean updateAgentApplyRecordSate(String recordId, String state);

	long getAmountByTime(Long startTime, Long endTime);

	List<AgentApplyRecord> findAgentApplyRecordByTime(Long startTime, Long endTime);

	AgentApplyRecord findAgentApplyRecordById(String recordId);
}
