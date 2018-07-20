package com.anbang.qipai.admin.plan.dao.agentsdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.agents.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentClubCardRecordDboVO;

public interface AgentClubCardRecordDboDao {
	void addAgentClubCardRecordDbo(AgentClubCardRecordDbo record);

	long getAmountByConditions(AgentClubCardRecordDboVO record);

	List<AgentClubCardRecordDbo> findAgentClubCardRecordDboByConditions(int page, int size,
			AgentClubCardRecordDboVO record);
}
