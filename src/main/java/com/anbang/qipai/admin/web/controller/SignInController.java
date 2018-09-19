package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.signin.SignInPrize;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeExchangeLog;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeLog;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeExchangeLogService;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeLogService;
import com.anbang.qipai.admin.plan.service.signinservice.SignInPrizeService;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * 签到Controller
 * 
 * @author created by hanzhuofan 2018.09.15
 */
@CrossOrigin
@RestController
@RequestMapping("/signin")
public class SignInController {

	@Autowired
	private SignInPrizeService signInPrizeService;

	@Autowired
	private SignInPrizeLogService signInPrizeLogService;

	@Autowired
	private SignInPrizeExchangeLogService signInPrizeExchangeLogService;

	@RequestMapping("/addsigninprize")
	public CommonVO addSignInPrize(SignInPrize signInPrize) {
		CommonVO vo = new CommonVO();
		if (signInPrize.getName() == null || signInPrize.getType() == null || signInPrize.getSingleNum() == 0
				|| signInPrize.getStoreNum() == 0 || signInPrize.getIconUrl() == null || signInPrize.getPrizeProb() == 0
				|| signInPrize.getFirstPrizeProb() == 0 || signInPrize.getOverstep() == null) {
			vo.setSuccess(false);
			vo.setMsg("incompleteParam");
			return vo;
		}
		int count = signInPrizeService.countSignInPrize();
		if (count >= 10) {
			vo.setSuccess(false);
			vo.setMsg("overstep");
			return vo;
		}
		signInPrizeService.addSignInPrize(signInPrize);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}

	@RequestMapping("/querysigninprize")
	public CommonVO querySignInPrize() {
		CommonVO vo = new CommonVO();
		List<SignInPrize> list = signInPrizeService.querySignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(list);
		return vo;
	}

	@RequestMapping("/querysigninprizebyid")
	public CommonVO querySignInPrizeById(String id) {
		CommonVO vo = new CommonVO();
		SignInPrize signInPrize = signInPrizeService.querySignInPrizeById(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(signInPrize);
		return vo;
	}

	@RequestMapping("/deletesigninprizebyid")
	public CommonVO deleteSignInPrizeById(String id) {
		CommonVO vo = new CommonVO();
		signInPrizeService.deleteSignInPrizeById(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}

	@RequestMapping("/updatesigninprize")
	public CommonVO updateSignInPrize(SignInPrize signInPrize) {
		CommonVO vo = new CommonVO();
		if (signInPrize.getId() == null || signInPrize.getName() == null || signInPrize.getType() == null
				|| signInPrize.getSingleNum() == 0 || signInPrize.getStoreNum() == 0 || signInPrize.getIconUrl() == null
				|| signInPrize.getPrizeProb() == 0 || signInPrize.getFirstPrizeProb() == 0
				|| signInPrize.getOverstep() == null) {
			vo.setSuccess(false);
			vo.setMsg("incompleteParam");
			return vo;
		}
		signInPrizeService.updateSignInPrize(signInPrize);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}

	@RequestMapping("/querysigninprizelog")
	public CommonVO querySignInPrizeLog(SignInPrizeLog signInPrizeLog,
			@RequestParam(value = "startTime", required = false) Long startTime,
			@RequestParam(value = "endTime", required = false) Long endTime) {
		CommonVO vo = new CommonVO();
		List<SignInPrizeLog> signInPrizeLogs = signInPrizeLogService.querySignInPrizeLog(signInPrizeLog, startTime,
				endTime);
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(signInPrizeLogs);
		return vo;
	}

	@RequestMapping("/querysigninprizeexchangelog")
	public CommonVO querySignInPrizeExchangeLog(SignInPrizeExchangeLog signInPrizeExchangeLog,
			@RequestParam(value = "startTime", required = false) Long startTime,
			@RequestParam(value = "endTime", required = false) Long endTime) {
		CommonVO vo = new CommonVO();
		List<SignInPrizeExchangeLog> list = signInPrizeExchangeLogService
				.querySignInPrizeExchangeLog(signInPrizeExchangeLog, startTime, endTime);
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(list);
		return vo;
	}

	/**
	 * 统计待发放奖品
	 * 
	 * @return
	 */
	@RequestMapping("/countunissuesigninprize")
	public CommonVO countUnIssueSignInPrize() {
		CommonVO vo = new CommonVO();
		int count = signInPrizeExchangeLogService.countUnIssueSignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(count);
		return vo;
	}

	@RequestMapping("/issuesigninprize")
	public CommonVO issueSignInPrize(int id) {
		CommonVO vo = new CommonVO();
		signInPrizeExchangeLogService.issueSignInPrize(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}

	@RequestMapping("/releasesigninprize")
	public CommonVO releaseSignInPrize() {
		CommonVO vo = new CommonVO();
		int count = signInPrizeService.countSignInPrize();
		if (count < 10) {
			vo.setSuccess(false);
			vo.setMsg("notEnough");
			vo.setData(count);
			return vo;
		}
		List<SignInPrize> list = signInPrizeService.querySignInPrize();
		int checkPrizeProb = 0;
		int checkFirstPrizeProb = 0;
		for (SignInPrize signInPrize : list) {
			checkPrizeProb += signInPrize.getPrizeProb();
			checkFirstPrizeProb += signInPrize.getFirstPrizeProb();
		}
		if (checkPrizeProb > 100) {
			vo.setSuccess(false);
			vo.setMsg("PrizeProbOverstep");
			vo.setData(checkPrizeProb);
			return vo;
		}
		if (checkFirstPrizeProb > 100) {
			vo.setSuccess(false);
			vo.setMsg("FirstPrizeProbOverstep");
			vo.setData(checkFirstPrizeProb);
			return vo;
		}
		signInPrizeService.releaseSignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}
}
