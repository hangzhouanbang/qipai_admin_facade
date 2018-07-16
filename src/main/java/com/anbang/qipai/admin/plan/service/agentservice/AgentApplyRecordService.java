package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentApplyRecordDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
import com.anbang.qipai.admin.web.vo.AgentApplyRecordVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentApplyRecordService {

	@Autowired
	private AgentApplyRecordDao agentApplyRecordDao;

	public void addAgentApplyRecord(AgentApplyRecord record) {
		agentApplyRecordDao.addAgentApplyRecord(record);
	}

	public boolean updateAgentApplyRecordSate(String recordId, String state) {
		return agentApplyRecordDao.updateAgentApplyRecordSate(recordId, state);
	}

	public ListPage findAgentApplyRecordByTime(int page, int size, AgentApplyRecordVO vo) {
		long amount = agentApplyRecordDao.getAmountByTime(vo.getStartTime(), vo.getEndTime());
		List<AgentApplyRecord> recordList = agentApplyRecordDao.findAgentApplyRecordByTime(vo.getStartTime(),
				vo.getEndTime());
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}

	public AgentApplyRecord findAgentApplyRecordById(String recordId) {
		return agentApplyRecordDao.findAgentApplyRecordById(recordId);
	}
}
