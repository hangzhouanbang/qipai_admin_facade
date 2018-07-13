package com.anbang.qipai.admin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentservice.AgentDboService;
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
public class ApplyController {

	private AgentDboService agentDboService;

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

}
