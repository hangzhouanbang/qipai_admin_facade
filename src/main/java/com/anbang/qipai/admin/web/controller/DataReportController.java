package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.report.GameDataReport;
import com.anbang.qipai.admin.plan.bean.report.PlatformReport;
import com.anbang.qipai.admin.plan.service.GameReportService;
import com.anbang.qipai.admin.plan.service.PlatformReportService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 数据报表controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/datareport")
public class DataReportController {

	@Autowired
	private PlatformReportService platformReportService;

	@Autowired
	private GameReportService gameReportService;

	@Autowired
	private MemberDboService memberService;

	@Autowired
	private MemberOrderService orderService;

	@Autowired
	private MemberLoginRecordService memberLoginRecordService;

	/**
	 * 平台运营数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/platformreport", method = RequestMethod.POST)
	public CommonVO platformOperateReport(@RequestParam(required = true) Long startTime,
			@RequestParam(required = true) Long endTime, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		CommonVO vo = new CommonVO();
		ListPage listPage = platformReportService.findPlatformReportByTime(page, size, startTime, endTime);
		vo.setSuccess(true);
		vo.setMsg("platformReportList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 游戏日报
	 * 
	 * @param startTime
	 * @param endTime
	 * @param game
	 * @return
	 */
	@RequestMapping(value = "/gamereport", method = RequestMethod.POST)
	public CommonVO gameReport(@RequestParam(required = true) Long startTime,
			@RequestParam(required = true) Long endTime, @RequestParam(required = true) String game) {
		CommonVO vo = new CommonVO();
		List<GameDataReport> reportList = gameReportService.findGameReportByTimeAndGame(startTime, endTime, game);
		vo.setSuccess(true);
		vo.setMsg("gameDataList");
		vo.setData(reportList);
		return vo;
	}

	/**
	 * 每日运营数据生成
	 */
	@Scheduled(cron = "0 0 2 * * ?") // 每天凌晨2点
	public void createPlatformReport() {
		long oneDay = 3600000 * 24;
		// 当日凌晨2点
		long endTime = System.currentTimeMillis();
		// 昨日凌晨2点
		long startTime = endTime - oneDay;
		int newMember = (int) memberService.countNewMemberByTime(startTime, endTime);
		int currentMember = (int) memberService.countVipMember();
		double cost = orderService.countCostByTime(startTime, endTime);
		int gameNum = gameReportService.countGameNumByTime(startTime, endTime);
		int loginMember = memberLoginRecordService.countLoginMemberByTime(startTime, endTime);
		int remainSecond = memberLoginRecordService.countRemainMemberByDeviationTime(oneDay);
		int remainThird = memberLoginRecordService.countRemainMemberByDeviationTime(oneDay * 2);
		int remainSeventh = memberLoginRecordService.countRemainMemberByDeviationTime(oneDay * 6);
		int remainMonth = memberLoginRecordService.countRemainMemberByDeviationTime(oneDay * 30);
		PlatformReport report = new PlatformReport(endTime, newMember, currentMember, cost, gameNum, loginMember,
				remainSecond, remainThird, remainSeventh, remainMonth);
		platformReportService.addPlatformReport(report);
	}

}
