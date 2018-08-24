package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.agents.AgentClubCard;
import com.anbang.qipai.admin.remote.vo.AccountRemoteVO;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-agents")
public interface QipaiAgentsRemoteService {
	@RequestMapping("/apply/applypass")
	public CommonRemoteVO apply_pass(@RequestParam(value = "recordId") String recordId);

	@RequestMapping("/apply/applyrefuse")
	public CommonRemoteVO apply_refuse(@RequestParam(value = "recordId") String recordId);

	@RequestMapping("/agent/agentaccount")
	public AccountRemoteVO agent_account(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/agent/setlevel")
	public CommonRemoteVO agent_setlevel(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "level") int level);

	@RequestMapping("/agent/setboss")
	public CommonRemoteVO agent_setboss(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "bossId") String bossId, @RequestParam(value = "bossName") String bossName);

	@RequestMapping("/agent/removeboss")
	public CommonRemoteVO agent_removeboss(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/agent/ban")
	public CommonRemoteVO agent_ban(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/agent/liberate")
	public CommonRemoteVO agent_liberate(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/clubcard/addagentclubcard")
	public CommonRemoteVO clubcard_addagentclubcard(@RequestBody AgentClubCard card);

	@RequestMapping("/clubcard/updateagentclubcard")
	public CommonRemoteVO clubcard_updateagentclubcard(@RequestBody AgentClubCard card);

	@RequestMapping("/clubcard/deleteagentclubcard")
	public CommonRemoteVO clubcard_deleteagentclubcard(@RequestParam(value = "agentId") String agentId);

	@RequestMapping("/score/withdraw")
	public CommonRemoteVO score_withdraw(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/score/givescoretoagent")
	public CommonRemoteVO score_givescoretoagent(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/withdrawclubcardzhou")
	public CommonRemoteVO clubcard_withdrawclubcardzhou(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/giveclubcardzhoutoagent")
	public CommonRemoteVO clubcard_giveclubcardzhoutoagent(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/withdrawclubcardyue")
	public CommonRemoteVO clubcard_withdrawclubcardyue(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/giveclubcardyuetoagent")
	public CommonRemoteVO clubcard_giveclubcardyuetoagent(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/withdrawclubcardji")
	public CommonRemoteVO clubcard_withdrawclubcardji(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping("/clubcard/giveclubcardjitoagent")
	public CommonRemoteVO clubcard_giveclubcardjitoagent(@RequestParam(value = "agentId") String agentId,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);
}
