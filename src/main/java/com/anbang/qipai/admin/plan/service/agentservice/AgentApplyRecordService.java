package com.anbang.qipai.admin.plan.service.agentservice;

import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentApplyRecordDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;

@Service
public class AgentApplyRecordService {

	private AgentApplyRecordDao agentApplyRecordDao;

	public void addAgentApplyRecord(AgentApplyRecord record) {
		agentApplyRecordDao.addAgentApplyRecord(record);
	}

	public boolean updateAgentApplyRecordSate(String recordId, String state) {
		return agentApplyRecordDao.updateAgentApplyRecordSate(recordId, state);
	}
}
