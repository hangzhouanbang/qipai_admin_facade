package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentsdao.AgentInvitationRecordDao;
import com.anbang.qipai.admin.plan.domain.agents.AgentInvitationRecord;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentInvitationRecordVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentInvitationRecordService {

	@Autowired
	private AgentInvitationRecordDao invitationRecordDao;

	public void addAgentInvitationRecord(AgentInvitationRecord record) {
		invitationRecordDao.addInvitationRecord(record);
	}

	public ListPage findInvitationRecordByConditions(int page, int size, AgentInvitationRecordVO record) {
		long amount = invitationRecordDao.getAmountByByConditions(record);
		List<AgentInvitationRecord> recordList = invitationRecordDao.findInvitationRecordByConditions(page, size,
				record);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}
}
