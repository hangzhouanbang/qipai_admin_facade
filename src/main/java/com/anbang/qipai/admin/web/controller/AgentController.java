package com.anbang.qipai.admin.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.agents.AgentApplyRecord;
import com.anbang.qipai.admin.plan.bean.agents.AgentClubCard;
import com.anbang.qipai.admin.plan.bean.agents.AgentDbo;
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
@CrossOrigin
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

	/**
	 * 查询推广员，不包括未通过申请的
	 * 
	 * @param page
	 * @param size
	 * @param agent
	 * @return
	 */
	@RequestMapping(value = "/queryagent", method = RequestMethod.POST)
	public CommonVO queryAgent(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
			AgentDboVO agent) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentDboService.findAgentDboByConditions(page, size, agent);
		vo.setSuccess(true);
		vo.setMsg("agentList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查看推广员详情
	 * 
	 * @param agentId
	 * @return
	 */
	@RequestMapping(value = "/agentdetail", method = RequestMethod.POST)
	public CommonVO queryAgentDetail(String agentId) {
		CommonVO vo = new CommonVO();
		Map<String, Object> data = new HashMap<String, Object>();
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		data.put("agent", agent);
		AccountRemoteVO accountRemoteVo = qipaiAgentsRemoteService.agent_account(agentId);
		if (accountRemoteVo.isSuccess()) {
			data.put("score", accountRemoteVo.getScore());
			data.put("clubCardZhou", accountRemoteVo.getClubCardZhou());
			data.put("clubCardYue", accountRemoteVo.getClubCardYue());
			data.put("clubCardJi", accountRemoteVo.getClubCardJi());
		}
		vo.setSuccess(true);
		vo.setMsg("agent detail");
		vo.setData(data);
		return vo;
	}

	/**
	 * 查看申请记录，包括已经处理过的
	 * 
	 * @param page
	 * @param size
	 * @param recordVo
	 * @return
	 */
	@RequestMapping(value = "/queryapplyrecord", method = RequestMethod.POST)
	public CommonVO queryApplyRecord(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, AgentApplyRecordVO recordVo) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentApplyRecordService.findAgentApplyRecordByByConditions(page, size, recordVo);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查询推广员商城会员卡
	 * 
	 * @param page
	 * @param size
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/queryagentclubcard", method = RequestMethod.POST)
	public CommonVO queryAgentClubCard(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, AgentClubCard card) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentClubCardService.findAgentClubCardByConditions(page, size, card);
		vo.setSuccess(true);
		vo.setMsg("cardList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查询推广员的会员卡流水
	 * 
	 * @param page
	 * @param size
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryclubcardrecord", method = RequestMethod.POST)
	public CommonVO queryClubCardRecord(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, AgentClubCardRecordDboVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentClubCardRecordDboService.findAgentClubCardRecordDboByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查询推广员的积分流水
	 * 
	 * @param page
	 * @param size
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryscorerecord", method = RequestMethod.POST)
	public CommonVO queryScoreRecord(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, AgentScoreRecordDboVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentScoreRecordDboService.findAgentScoreRecordDboByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/query_agent_score", method = RequestMethod.POST)
	public CommonVO queryAgentCurrentScore(String agentId) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(true);
		return vo;
	}

	@RequestMapping(value = "/query_agent_club", method = RequestMethod.POST)
	public CommonVO queryAgentCurrentCard(String agentId) {
		CommonVO vo = new CommonVO();
		return vo;
	}

	/**
	 * 查询推广员的邀请记录
	 * 
	 * @param page
	 * @param size
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryagentinvitationrecord", method = RequestMethod.POST)
	public CommonVO queryAgentInvitationRecord(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, AgentInvitationRecordVO record) {
		CommonVO vo = new CommonVO();
		ListPage listPage = agentInvitationRecordService.findInvitationRecordByConditions(page, size, record);
		vo.setSuccess(true);
		vo.setMsg("recordList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 推广员申请通过
	 * 
	 * @param recordId
	 * @return
	 */
	@RequestMapping(value = "/applypass", method = RequestMethod.POST)
	public CommonVO applyPass(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (record == null) {
			vo.setSuccess(false);
			vo.setMsg("not found apply record");
			return vo;
		}
		CommonRemoteVO rvo = qipaiAgentsRemoteService.apply_pass(recordId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 推广员申请拒绝
	 * 
	 * @param recordId
	 * @return
	 */
	@RequestMapping(value = "/applyrefuse", method = RequestMethod.POST)
	public CommonVO applyRefuse(String recordId) {
		CommonVO vo = new CommonVO();
		AgentApplyRecord record = agentApplyRecordService.findAgentApplyRecordById(recordId);
		if (record == null) {
			vo.setSuccess(false);
			vo.setMsg("not found apply record");
			return vo;
		}
		CommonRemoteVO rvo = qipaiAgentsRemoteService.apply_refuse(recordId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 设置推广员等级,默认2级
	 * 
	 * @param agentId
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/setlevel", method = RequestMethod.POST)
	public CommonVO setLevel(String agentId, @RequestParam(defaultValue = "2") int level) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiAgentsRemoteService.agent_setlevel(agentId, level);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 绑定推广员，设置上下级
	 * 
	 * @param agentId
	 * @param bossId
	 * @return
	 */
	@RequestMapping(value = "/setboss", method = RequestMethod.POST)
	public CommonVO setBoss(String agentId, String bossId) {
		CommonVO vo = new CommonVO();
		AgentDbo boss = agentDboService.findAgentDboById(bossId);
		if (boss == null || boss.getLevel() != 1) {
			vo.setSuccess(false);
			vo.setMsg("boss not found");
			return vo;
		}
		CommonRemoteVO rvo = qipaiAgentsRemoteService.agent_setboss(agentId, bossId, boss.getNickname());
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 解除绑定
	 * 
	 * @param agentId
	 * @return
	 */
	@RequestMapping(value = "/removeboss", method = RequestMethod.POST)
	public CommonVO removeBoss(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiAgentsRemoteService.agent_removeboss(agentId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 封禁推广员
	 * 
	 * @param agentId
	 * @return
	 */
	@RequestMapping(value = "/ban", method = RequestMethod.POST)
	public CommonVO ban(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiAgentsRemoteService.agent_ban(agentId);
		vo.setSuccess(rvo.isSuccess());
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		vo.setData(agent.getState());
		return vo;
	}

	/**
	 * 推广员当前会员卡数量
	 *
	 * @param agentId
	 * @param cardType
	 * @return
	 */
	@RequestMapping(value = "/card_amount", method = RequestMethod.POST)
	public CommonVO cardAmount(String agentId, String cardType) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(true);
		if (!Arrays.asList("周卡", "月卡", "季卡").contains(cardType)) {
			vo.setSuccess(false);
			vo.setMsg("卡类型必须为:[周卡,月卡,季卡]");
		}
		CommonRemoteVO commonRemoteVO = this.qipaiAgentsRemoteService.query_club_card_amount(agentId, cardType);
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(commonRemoteVO.getData());
		return vo;
	}

	@RequestMapping(value = "/score_amount", method = RequestMethod.POST)
	public CommonVO scoreAmount(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = this.qipaiAgentsRemoteService.query_score_amount(agentId);
		vo.setData(commonRemoteVO.getData());
		vo.setSuccess(true);
		return vo;
	}

	/**
	 * 解封推广员
	 * 
	 * @param agentId
	 * @return
	 */
	@RequestMapping(value = "/liberate", method = RequestMethod.POST)
	public CommonVO liberate(String agentId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiAgentsRemoteService.agent_liberate(agentId);
		vo.setSuccess(rvo.isSuccess());
		AgentDbo agent = agentDboService.findAgentDboById(agentId);
		vo.setData(agent.getState());
		return vo;
	}

	/**
	 * 添加推广员商城会员卡，包括积分会员卡和人民币会员卡
	 * 
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/addagentclubcard", method = RequestMethod.POST)
	public CommonVO addAgentClubCard(AgentClubCard card) {
		CommonVO vo = new CommonVO();
		if (card.getProduct() == null || card.getProductPic() == null || card.getPayType() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
		}
		card.setSale(true);
		CommonRemoteVO rvo = qipaiAgentsRemoteService.clubcard_addagentclubcard(card);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 修改推广员商城会员卡
	 * 
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/updateagentclubcard", method = RequestMethod.POST)
	public CommonVO updateAgentClubCard(AgentClubCard card) {
		CommonVO vo = new CommonVO();
		if (card.getProduct() == null || card.getProductPic() == null || card.getPayType() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
		}
		CommonRemoteVO rvo = qipaiAgentsRemoteService.clubcard_updateagentclubcard(card);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 删除推广员会员卡
	 * 
	 * @param cardIds
	 * @return
	 */
	@RequestMapping(value = "/deleteagentclubcard", method = RequestMethod.POST)
	public CommonVO deleteAgentClubCard(String cardId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiAgentsRemoteService.clubcard_deleteagentclubcard(cardId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 调整推广员积分
	 * 
	 * @param agentId
	 * @param scoreAmount
	 * @return
	 */
	@RequestMapping(value = "/scoremanager", method = RequestMethod.POST)
	public CommonVO scoreManager(String agentId, int scoreAmount) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = new CommonRemoteVO();
		if (scoreAmount < 0) {
			rvo = qipaiAgentsRemoteService.score_withdraw(agentId, -scoreAmount, "管理员调整");
		}
		if (scoreAmount > 0) {
			rvo = qipaiAgentsRemoteService.score_givescoretoagent(agentId, scoreAmount, "管理员调整");
		}
		vo.setSuccess(rvo.isSuccess());
		vo.setMsg(rvo.getMsg());
		return vo;
	}

	/**
	 * 调整推广员会员卡
	 * 
	 * @param agentId
	 * @param card
	 * @param cardAmount
	 * @param scoreAmount
	 * @return
	 */
	@RequestMapping(value = "/clubcardmanager", method = RequestMethod.POST)
	public CommonVO clubcardManager(String agentId, String card, int cardAmount, int scoreAmount) {
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
