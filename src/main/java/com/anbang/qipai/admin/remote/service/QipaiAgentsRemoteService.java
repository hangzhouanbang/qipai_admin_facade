package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.agent.AgentCostClubCard;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreClubCard;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-agents")
public interface QipaiAgentsRemoteService {
	@RequestMapping("/apply/applypass")
	public CommonRemoteVO apply_pass(@RequestParam(value = "recordId") String recordId);

	@RequestMapping("/apply/applyrefuse")
	public CommonRemoteVO apply_refuse(@RequestParam(value = "recordId") String recordId);

	@RequestMapping("/agent/setlevel")
	public CommonRemoteVO agent_setlevel(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "level") int level);

	@RequestMapping("/agent/setboss")
	public CommonRemoteVO agent_setboss(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "bossId") String bossId, @RequestParam(value = "bossName") String bossName);

	@RequestMapping("/agent/ban")
	public CommonRemoteVO agent_ban(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/agent/liberate")
	public CommonRemoteVO agent_liberate(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/clubcard/addscoreclubcard")
	public CommonRemoteVO clubcard_addscoreclubcard(@RequestBody AgentScoreClubCard card);

	@RequestMapping("/clubcard/addcostclubcard")
	public CommonRemoteVO clubcard_addcostclubcard(@RequestBody AgentCostClubCard card);

	@RequestMapping("/clubcard/deletescoreclubcard")
	public CommonRemoteVO clubcard_deletescoreclubcard(@RequestBody String[] cardIds);

	@RequestMapping("/clubcard/deletecostclubcard")
	public CommonRemoteVO clubcard_deletecostclubcard(@RequestBody String[] cardIds);
}
