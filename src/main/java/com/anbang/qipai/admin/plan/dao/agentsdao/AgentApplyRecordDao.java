package com.anbang.qipai.admin.plan.dao.agentsdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agents.AgentApplyRecord;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentApplyRecordVO;

public interface AgentApplyRecordDao {

	void addAgentApplyRecord(AgentApplyRecord record);

	boolean updateAgentApplyRecordSate(String recordId, String state);

	long getAmountByConditions(int page, int size, AgentApplyRecordVO record);

	List<AgentApplyRecord> findAgentApplyRecordByConditions(int page, int size, AgentApplyRecordVO record);

	AgentApplyRecord findAgentApplyRecordById(String recordId);
}
