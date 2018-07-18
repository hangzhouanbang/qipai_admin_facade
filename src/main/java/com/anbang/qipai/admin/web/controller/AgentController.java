package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.config.AgentApplyState;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
import com.anbang.qipai.admin.plan.domain.agent.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.plan.domain.agent.AgentCostClubCard;
import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreClubCard;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreRecordDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentApplyRecordService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentClubCardRecordDboService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentCostClubCardService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentDboService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentScoreClubCardService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentScoreRecordDboService;
import com.anbang.qipai.admin.remote.service.QipaiAgentsRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.AgentApplyRecordVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 推广员controller
 * 
 * @author 林少聪 2018.7.13
 *
 */
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentApplyRecordService agentApplyRecordService;

	@Autowired
	private AgentDboService agentDboService;

	@Autowired
	private AgentCostClubCardService agentCostClubCardService;

	@Autowired
	private AgentScoreClubCardService agentScoreClubCardService;

	@Autowired
	private AgentClubCardRecordDboService agentClubCardRecordDboService;

	@Autowired
	private AgentScoreRecordDboService agentScoreRecordDboService;

	@Autowired
	private QipaiAgentsRemoteService qipaiAgentsRemoteService;

	@RequestMapping("/queryagent")
	public CommonVO queryAgent(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentDbo agent) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentDboService.findAgentDboByConditions(page, size, agent);
		vo.setSuccess(true);
		vo.setMsg("agentList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryapplyrecord")
	public CommonVO queryApplyRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentApplyRecordVO recordVo) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentApplyRecordService.findAgentApplyRecordByTime(page, size, recordVo);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryclubcardrecord")
	public CommonVO queryClubCardRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentClubCardRecordDbo dbo) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentClubCardRecordDboService.findAgentClubCardRecordDboByConditions(page, size, dbo);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryscorerecord")
	public CommonVO queryScoreRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentScoreRecordDbo dbo) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentScoreRecordDboService.findAgentScoreRecordDboByConditions(page, size, dbo);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/applypass")
	public CommonVO applyPass(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (!AgentApplyState.APPLYING.equals(record.getState())) {
			vo.setSuccess(false);
			vo.setMsg("this apply has already disposed");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.apply_pass(recordId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			record.setAgentId((String) map.get("agentId"));
			record.setPhone((String) map.get("phone"));
			record.setUserName((String) map.get("userName"));
			record.setIdCard((String) map.get("idCard"));
			record.setFrontUrl((String) map.get("frontUrl"));
			record.setReverseUrl((String) map.get("reverseUrl"));
			agentDboService.updateAgnetInfo(record);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/applyrefuse")
	public CommonVO applyRefuse(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (!AgentApplyState.APPLYING.equals(record.getState())) {
			vo.setSuccess(false);
			vo.setMsg("this apply has already disposed");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.apply_refuse(recordId);
		if (commonRemoteVO.isSuccess()) {
			agentApplyRecordService.updateAgentApplyRecordSate(recordId, AgentApplyState.APPLYFAIL);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/setlevel")
	public CommonVO setLevel(@RequestParam(required = true) String agentId,
			@RequestParam(required = true) Integer level) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_setlevel(agentId, level);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.updateAgentDboLevel(agentId, level);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/setboss")
	public CommonVO setBoss(@RequestParam(required = true) String agentId,
			@RequestParam(required = true) String bossId) {
		CommonVO vo = new CommonVO();
		AgentDbo boss = agentDboService.findAgentDboById(bossId);
		if (boss == null) {
			vo.setSuccess(false);
			vo.setMsg("boss not found");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_setboss(agentId, bossId, boss.getBossName());
		if (commonRemoteVO.isSuccess()) {
			agentDboService.updateAgentDboBoss(agentId, bossId, boss.getBossName());
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}
	
	@RequestMapping("/removeboss")
	public CommonVO removeBoss(@RequestParam(required = true) String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_removeboss(agentId);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.removeAgentDboBoss(agentId);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/ban")
	public CommonVO ban(@RequestParam(required = true) String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_ban(agentId);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.banAgentDboState(agentId);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/liberate")
	public CommonVO liberate(@RequestParam(required = true) String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_liberate(agentId);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.liberateAgentDboState(agentId);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/addscoreclubcard")
	public CommonVO addScoreClubCard(AgentScoreClubCard card) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_addscoreclubcard(card);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			card.setId((String) map.get("id"));
			agentScoreClubCardService.addScoreClubCard(card);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/addcostclubcard")
	public CommonVO addCostClubCard(AgentCostClubCard card) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_addcostclubcard(card);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			card.setId((String) map.get("id"));
			agentCostClubCardService.addCostClubCard(card);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deletescoreclubcard")
	public CommonVO deleteScoreClubCard(String[] cardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_deletescoreclubcard(cardIds);
		if (commonRemoteVO.isSuccess()) {
			agentCostClubCardService.deleteCostClubCard(cardIds);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deletecostclubcard")
	public CommonVO deleteCostClubCard(String[] cardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_deletecostclubcard(cardIds);
		if (commonRemoteVO.isSuccess()) {
			agentCostClubCardService.deleteCostClubCard(cardIds);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}
}
