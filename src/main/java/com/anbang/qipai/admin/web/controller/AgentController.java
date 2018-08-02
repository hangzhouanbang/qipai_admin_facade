package com.anbang.qipai.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.agents.AgentApplyRecord;
import com.anbang.qipai.admin.plan.domain.agents.AgentClubCard;
import com.anbang.qipai.admin.plan.domain.agents.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentApplyRecordService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentClubCardRecordDboService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentClubCardService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentDboService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentInvitationRecordService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentScoreRecordDboService;
import com.anbang.qipai.admin.remote.service.QipaiAgentsRemoteService;
import com.anbang.qipai.admin.remote.vo.AccountRemoteVO;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentApplyRecordVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentClubCardRecordDboVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentInvitationRecordVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentScoreRecordDboVO;
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
	private AgentClubCardService agentClubCardService;

	@Autowired
	private AgentClubCardRecordDboService agentClubCardRecordDboService;

	@Autowired
	private AgentScoreRecordDboService agentScoreRecordDboService;

	@Autowired
	private QipaiAgentsRemoteService qipaiAgentsRemoteService;

	@Autowired
	private AgentInvitationRecordService agentInvitationRecordService;

	@RequestMapping("/queryagent")
	public CommonVO queryAgent(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentDboVO agent) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentDboService.findAgentDboByConditions(page, size, agent);
		vo.setSuccess(true);
		vo.setMsg("agentList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/agentdetail")
	public CommonVO queryAgentDetail(String agentId) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(false);
		Map<String, Object> map = new HashMap<String, Object>();
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		AccountRemoteVO accountRemoteVo = qipaiAgentsRemoteService.agent_account(agentId);
		map.put("agent", agent);
		if (accountRemoteVo.isSuccess()) {
			map.put("score", accountRemoteVo.getScore());
			map.put("clubCardZhou", accountRemoteVo.getClubCardZhou());
			map.put("clubCardYue", accountRemoteVo.getClubCardYue());
			map.put("clubCardJi", accountRemoteVo.getClubCardJi());
			vo.setSuccess(true);
			vo.setMsg("agent detail");
			vo.setData(map);
		}
		return vo;
	}

	@RequestMapping("/queryapplyrecord")
	public CommonVO queryApplyRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentApplyRecordVO recordVo) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentApplyRecordService.findAgentApplyRecordByByConditions(page, size, recordVo);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryagentclubcard")
	public CommonVO queryAgentClubCard(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentClubCard card) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentClubCardService.findAgentClubCardByConditions(page, size, card);
		vo.setSuccess(true);
		vo.setMsg("cardList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryclubcardrecord")
	public CommonVO queryClubCardRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentClubCardRecordDboVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentClubCardRecordDboService.findAgentClubCardRecordDboByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryscorerecord")
	public CommonVO queryScoreRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentScoreRecordDboVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentScoreRecordDboService.findAgentScoreRecordDboByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryagentinvitationrecord")
	public CommonVO queryAgentInvitationRecord(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, AgentInvitationRecordVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentInvitationRecordService.findInvitationRecordByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/applypass")
	public CommonVO applyPass(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (record == null) {
			vo.setSuccess(false);
			vo.setMsg("not found apply record");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.apply_pass(recordId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			agentApplyRecordService.updateAgentApplyRecordSate(recordId, (String) map.get("state"));
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/applyrefuse")
	public CommonVO applyRefuse(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (record == null) {
			vo.setSuccess(false);
			vo.setMsg("not found apply record");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.apply_refuse(recordId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			agentApplyRecordService.updateAgentApplyRecordSate(recordId, (String) map.get("state"));
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/setlevel")
	public CommonVO setLevel(String agentId, Integer level) {
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
	public CommonVO setBoss(String agentId, String bossId) {
		CommonVO vo = new CommonVO();
		AgentDbo boss = agentDboService.findAgentDboById(bossId);
		if (boss == null || boss.getLevel() != 1) {
			vo.setSuccess(false);
			vo.setMsg("boss not found");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_setboss(agentId, bossId, boss.getNickname());
		if (commonRemoteVO.isSuccess()) {
			agentDboService.updateAgentDboBoss(agentId, bossId, boss.getNickname());
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/removeboss")
	public CommonVO removeBoss(String agentId) {
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
	public CommonVO ban(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_ban(agentId);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.banAgentDboState(agentId);
		}
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(agent.getState());
		return vo;
	}

	@RequestMapping("/liberate")
	public CommonVO liberate(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.agent_liberate(agentId);
		if (commonRemoteVO.isSuccess()) {
			agentDboService.liberateAgentDboState(agentId);
		}
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(agent.getState());
		return vo;
	}

	@RequestMapping("/addagentclubcard")
	public CommonVO addAgentClubCard(AgentClubCard card) {
		CommonVO vo = new CommonVO();
		if (card.getProduct() == null || card.getProductPic() == null || card.getNumber() == null
				|| card.getRepertory() == null || card.getPayType() == null || card.getPrice() == null
				|| card.getWeight() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
		}
		card.setSale(true);
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_addagentclubcard(card);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			card.setId((String) map.get("id"));
			agentClubCardService.addAgentClubCard(card);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/updateagentclubcard")
	public CommonVO updateAgentClubCard(AgentClubCard card) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_updateagentclubcard(card);
		if (commonRemoteVO.isSuccess()) {
			agentClubCardService.updateAgentClubCard(card);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deleteagentclubcard")
	public CommonVO deleteAgentClubCard(@RequestParam(value = "cardId") String[] cardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiAgentsRemoteService.clubcard_deleteagentclubcard(cardIds);
		if (commonRemoteVO.isSuccess()) {
			agentClubCardService.deleteAgentClubCard(cardIds);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/clubcardmanager")
	public CommonVO clubCardManager(String agentId, String card, int cardAmount) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = new CommonRemoteVO();
		if (cardAmount < 0) {
			commonRemoteVO = qipaiAgentsRemoteService.score_withdraw(agentId, -cardAmount, "管理员调整");
		}
		if (cardAmount > 0) {
			commonRemoteVO = qipaiAgentsRemoteService.score_withdraw(agentId, cardAmount, "管理员调整");
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/scoremanager")
	public CommonVO scoreManager(String agentId, String card, int cardAmount, int scoreAmount) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO cardVO = new CommonRemoteVO();
		if ("周卡".equals(card)) {
			if (cardAmount < 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_withdrawclubcardzhou(agentId, -cardAmount, "管理员调整");
			}
			if (cardAmount > 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_giveclubcardzhoutoagent(agentId, cardAmount, "管理员调整");
			}
		}
		if ("月卡".equals(card)) {
			if (cardAmount < 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_withdrawclubcardyue(agentId, -cardAmount, "管理员调整");
			}
			if (cardAmount > 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_giveclubcardyuetoagent(agentId, cardAmount, "管理员调整");
			}
		}
		if ("季卡".equals(card)) {
			if (cardAmount < 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_withdrawclubcardji(agentId, -cardAmount, "管理员调整");
			}
			if (cardAmount > 0) {
				cardVO = qipaiAgentsRemoteService.clubcard_giveclubcardjitoagent(agentId, cardAmount, "管理员调整");
			}
		}
		CommonRemoteVO scoreVO = new CommonRemoteVO();
		if (scoreAmount < 0) {
			scoreVO = qipaiAgentsRemoteService.score_withdraw(agentId, -scoreAmount, "管理员调整");
		}
		if (scoreAmount > 0) {
			scoreVO = qipaiAgentsRemoteService.score_givescoretoagent(agentId, scoreAmount, "管理员调整");
		}
		if (!cardVO.isSuccess() || !scoreVO.isSuccess()) {
			vo.setSuccess(false);
		}
		if (cardVO.getMsg() != null) {
			vo.setMsg(cardVO.getMsg());
		}
		if (scoreVO.getMsg() != null) {
			vo.setMsg(scoreVO.getMsg());
		}
		return vo;
	}
}
