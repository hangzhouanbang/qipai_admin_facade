package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * 签到抽奖Controller
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
	//添加抽奖奖励
	@RequestMapping(value = "/addsigninprize", method = RequestMethod.POST)
	public CommonVO addSignInPrize(SignInPrize signInPrize) {
		CommonVO vo = new CommonVO();
		if (signInPrize.getName() == null || 
			signInPrize.getSingleNum() == 0 || 
			signInPrize.getStoreNum() == 0 || 
			signInPrize.getIconUrl() == null || 
			signInPrize.getPrizeProb() == 0 || 
			signInPrize.getFirstPrizeProb() == 0) {
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
	//查询所有抽奖奖励
	@RequestMapping(value = "/querysigninprize", method = RequestMethod.POST)
	public CommonVO querySignInPrize() {
		CommonVO vo = new CommonVO();
		List<SignInPrize> list = signInPrizeService.querySignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(list);
		return vo;
	}
	//根据id查询抽奖奖励
	@RequestMapping(value = "/querysigninprizebyid", method = RequestMethod.POST)
	public CommonVO querySignInPrizeById(String id) {
		CommonVO vo = new CommonVO();
		SignInPrize signInPrize = signInPrizeService.querySignInPrizeById(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(signInPrize);
		return vo;
	}
	//根据id删除抽奖奖励
	@RequestMapping(value = "/deletesigninprizebyid", method = RequestMethod.POST)
	public CommonVO deleteSignInPrizeById(String id) {
		CommonVO vo = new CommonVO();
		signInPrizeService.deleteSignInPrizeById(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}
	//修改抽奖奖品(无论是否发布，修改后都要把state设置为1)
	@RequestMapping(value = "/updatesigninprize", method = RequestMethod.POST)
	public CommonVO updateSignInPrize(SignInPrize signInPrize) {
		CommonVO vo = new CommonVO();
		signInPrizeService.updateSignInPrize(signInPrize);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}
	
	//发布10个抽奖奖励
	@RequestMapping(value = "/releasesigninprize", method = RequestMethod.POST)
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
		long checkPrizeProb = 0;
		long checkFirstPrizeProb = 0;
		for (SignInPrize signInPrize : list) {
			checkPrizeProb += signInPrize.getPrizeProb();
			checkFirstPrizeProb += signInPrize.getFirstPrizeProb();
			//发布改变状态为可减库存
			signInPrize.setState("0");
		}		
		if (checkPrizeProb != 10000000) {
			vo.setSuccess(false);
			vo.setMsg("中奖概率设置有误");
			//vo.setData(checkPrizeProb);
			return vo;
		}
		if (checkFirstPrizeProb != 10000000) {
			vo.setSuccess(false);
			vo.setMsg("首次中奖概率设置有误");
			//vo.setData(checkFirstPrizeProb);
			return vo;
		}
		// kafka发消息
		signInPrizeService.releaseSignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}	
	
	//查询抽奖中奖纪录
	@RequestMapping(value = "/querysigninprizelog", method = RequestMethod.POST)
	public CommonVO querySignInPrizeLog(SignInPrizeLog signInPrizeLog,
			@RequestParam(value = "startTime", required = false) Long startTime,
			@RequestParam(value = "endTime", required = false) Long endTime) {
		CommonVO vo = new CommonVO();
		List<SignInPrizeLog> signInPrizeLogs = 
				signInPrizeLogService.querySignInPrizeLog(signInPrizeLog, startTime,endTime);
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(signInPrizeLogs);
		return vo;
	}
	//查询抽奖奖品兑换纪录
	@RequestMapping(value = "/querysigninprizeexchangelog", method = RequestMethod.POST)
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
	 * 统计抽奖奖品个数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/countunissuesigninprize", method = RequestMethod.POST)
	public CommonVO countUnIssueSignInPrize() {
		CommonVO vo = new CommonVO();
		int count = signInPrizeExchangeLogService.countUnIssueSignInPrize();
		vo.setSuccess(true);
		vo.setMsg("success");
		vo.setData(count);
		return vo;
	}
	//发放兑换记录奖品
	@RequestMapping(value = "/issuesigninprize", method = RequestMethod.POST)
	public CommonVO issueSignInPrize(int id) {
		CommonVO vo = new CommonVO();
		signInPrizeExchangeLogService.issueSignInPrize(id);
		vo.setSuccess(true);
		vo.setMsg("success");
		return vo;
	}

}
