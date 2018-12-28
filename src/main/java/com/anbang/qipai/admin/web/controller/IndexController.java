package com.anbang.qipai.admin.web.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.agentsservice.AgentClubCardRecordDboService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;

@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private AgentDboService agentDboService;

	@Autowired
	private MemberDboService memberService;

	@Autowired
	private MemberOrderService orderService;

	@Autowired
	private AgentClubCardRecordDboService agentClubCardRecordDboService;

	@RequestMapping("/memberindex")
	public CommonVO memberIndex(@RequestParam(defaultValue = "-1") Integer month) {
		CommonVO vo = new CommonVO();
		Map<String, Object> data = new HashMap<String, Object>();
		long amount = memberService.countAmount();
		data.put("amount", amount);
		long vipAmount = memberService.countVipMember();
		data.put("vipAmount", vipAmount);
		Calendar calendar = Calendar.getInstance();
		if (month < 12 && month > -1) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startTime = calendar.getTimeInMillis();// 当月第一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long endTime = calendar.getTimeInMillis();// 下月第一天
		int memberRiAmount = orderService.countProductNumByTimeAndProduct("日卡", startTime, endTime);
		data.put("memberRiAmount", memberRiAmount);
		int memberZhouAmount = orderService.countProductNumByTimeAndProduct("周卡", startTime, endTime);
		data.put("memberZhouAmount", memberZhouAmount);
		int memberYueAmount = orderService.countProductNumByTimeAndProduct("月卡", startTime, endTime);
		data.put("memberYueAmount", memberYueAmount);
		int memberJiAmount = orderService.countProductNumByTimeAndProduct("季卡", startTime, endTime);
		data.put("memberJiAmount", memberJiAmount);
		vo.setSuccess(true);
		vo.setMsg("index info");
		vo.setData(data);
		return vo;
	}

	@RequestMapping("/agentindex")
	public CommonVO agentIndex(@RequestParam(defaultValue = "-1") Integer month) {
		CommonVO vo = new CommonVO();
		Map<String, Object> data = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		if (month < 12 && month > -1) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startTime = calendar.getTimeInMillis();// 当月第一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long endTime = calendar.getTimeInMillis();// 下月第一天
		long amount = agentDboService.countAmountByConditions(new AgentDboVO());
		data.put("amount", amount);
		int agentRiAmount = agentClubCardRecordDboService.countProductNumByTimeAndProduct("日卡", "buy", startTime,
				endTime);
		data.put("agentRiAmount", agentRiAmount);
		int agentZhouAmount = agentClubCardRecordDboService.countProductNumByTimeAndProduct("周卡", "buy", startTime,
				endTime);
		data.put("agentZhouAmount", agentZhouAmount);
		int agentYueAmount = agentClubCardRecordDboService.countProductNumByTimeAndProduct("月卡", "buy", startTime,
				endTime);
		data.put("agentYueAmount", agentYueAmount);
		int agentJiAmount = agentClubCardRecordDboService.countProductNumByTimeAndProduct("季卡", "buy", startTime,
				endTime);
		data.put("agentJiAmount", agentJiAmount);
		int agentGoldAmount = agentClubCardRecordDboService.countProductNumByTimeAndProduct("玉石", "buy", startTime,
				endTime);
		data.put("agentGoldAmount", agentGoldAmount);
		vo.setSuccess(true);
		vo.setMsg("index info");
		vo.setData(data);
		return vo;
	}
}
