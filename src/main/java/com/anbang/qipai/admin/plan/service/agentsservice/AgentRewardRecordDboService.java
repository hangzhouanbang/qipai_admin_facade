package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.ArrayList;
import java.util.List;

import com.anbang.qipai.admin.plan.bean.agents.AgentRewardType;
import com.dml.accounting.TextAccountingSummary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentBackRewardRecordDbo;
import com.anbang.qipai.admin.plan.bean.agents.AgentRewardRecordDbo;
import com.anbang.qipai.admin.plan.bean.agents.AgentWithdrawRecordDbo;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentBackRewardRecordDboDao;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentRewardRecordDboDao;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentWithdrawRecordDboDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentRewardRecordDboVO;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentRewardRecordDboService {

	@Autowired
	private AgentRewardRecordDboDao agentRewardRecordDboDao;

	@Autowired
	private AgentWithdrawRecordDboDao agentWithdrawRecordDboDao;

	@Autowired
	private AgentBackRewardRecordDboDao agentBackRewardRecordDboDao;

	public void addAgentRewardRecordDbo(AgentRewardRecordDbo record) {
		agentRewardRecordDboDao.addAgentRewardRecordDbo(record);
	}

	public void addAgentWithdrawRecordDbo(AgentWithdrawRecordDbo record) {
		agentWithdrawRecordDboDao.save(record);
	}

	public void addAgentBackRewardRecordDbo(AgentBackRewardRecordDbo record) {
		agentBackRewardRecordDboDao.save(record);
	}

	public ListPage findAgentRewardRecordDboByConditions(int page, int size, AgentRewardRecordDboVO record) {
		long amount = agentRewardRecordDboDao.getAmountByConditions(record);
		List<AgentRewardRecordDbo> recordList = agentRewardRecordDboDao.findAgentRewardRecordDboByConditions(page, size,
				record);
		List<AgentRewardRecordDboVO> rewardRecordDboVOS = new ArrayList<>();
		for (AgentRewardRecordDbo list : recordList) {
			AgentRewardRecordDboVO vo = new AgentRewardRecordDboVO();
			TextAccountingSummary summary = (TextAccountingSummary) list.getSummary();
			BeanUtils.copyProperties(list, vo);
			vo.setRewardType(AgentRewardType.getRewardType(summary.getText()));
			rewardRecordDboVOS.add(vo);
		}
		ListPage listPage = new ListPage(rewardRecordDboVOS, page, size, (int) amount);
		return listPage;
	}

	public ListPage findAgentWithdrawRecordDboByConditions(int page, int size, AgentWithdrawRecordDbo record) {
		long amount = agentWithdrawRecordDboDao.countAmountByConditions(record);
		List<AgentWithdrawRecordDbo> recordList = agentWithdrawRecordDboDao.findByConditions(page, size, record);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}

	public long countAgentWithdrawRecordDboAmountByApplying() {
		AgentWithdrawRecordDbo record = new AgentWithdrawRecordDbo();
		record.setState("APPLYING");
		long amount = agentWithdrawRecordDboDao.countAmountByConditions(record);
		return amount;
	}

	public void agentRewardApplyPass(String id, String state) {
		agentWithdrawRecordDboDao.updateAgentWithdrawRecordDboState(id, state);
	}

	public void agentRewardApplyRefuse(String id, String state, String desc) {
		agentWithdrawRecordDboDao.updateAgentWithdrawRecordDboDescAndState(id, state, desc);
	}

	public double findAmountByConditions(AgentRewardRecordDboVO record) {
		return agentRewardRecordDboDao.findAmountByConditions(record);
	}

}
