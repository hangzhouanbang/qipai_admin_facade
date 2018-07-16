package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.config.AgentApplyState;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentApplyRecordService;
import com.anbang.qipai.admin.plan.service.agentservice.AgentDboService;
import com.anbang.qipai.admin.remote.service.QipaiAgentsRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.AgentApplyRecordVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 推广员申请controller
 * 
 * @author 林少聪 2018.7.13
 *
 */
@RestController
@RequestMapping("/apply")
public class AgentController {

	@Autowired
	private AgentApplyRecordService agentApplyRecordService;

	@Autowired
	private AgentDboService agentDboService;

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
}
