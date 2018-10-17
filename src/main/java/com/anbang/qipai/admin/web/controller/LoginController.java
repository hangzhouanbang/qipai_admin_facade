package com.anbang.qipai.admin.web.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentClubCardRecordDboService;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * 登录controller
 * 
 * @author 林少聪 2018.5.31
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminAuthService adminAuthService;

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
		long seniorAmount = agentDboService.countAmountByLevel(1);
		data.put("seniorAmount", seniorAmount);
		long juniorAmount = agentDboService.countAmountByLevel(2);
		data.put("juniorAmount", juniorAmount);
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
		vo.setSuccess(true);
		vo.setMsg("index info");
		vo.setData(data);
		return vo;
	}

	/**
	 * 管理员登录
	 * 
	 */
	@RequestMapping("/login")
	public CommonVO login(@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "pass", required = true) String pass) {
		CommonVO vo = new CommonVO();
		Admin admin = adminService.verifyAdmin(nickname, pass);
		if (admin != null) {
			String token = adminAuthService.thirdAuth(admin.getId());
			vo.setSuccess(true);
			vo.setMsg("token");
			Map data = new HashMap();
			admin.setPass(null);
			data.put("admin", admin);
			data.put("token", token);
			vo.setData(data);
			return vo;
		}
		vo.setSuccess(false);
		return vo;
	}

	/**
	 * 管理员登出
	 * 
	 */
	@RequestMapping("/logout")
	public CommonVO logout(String token) {
		CommonVO vo = new CommonVO();
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId != null) {
			adminAuthService.removeSessionByAdminId(adminId);
			vo.setMsg("invalid token");
		}
		return vo;
	}
}
