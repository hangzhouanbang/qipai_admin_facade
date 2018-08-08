package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.GameDataReport;
import com.anbang.qipai.admin.plan.bean.PlatformReport;
import com.anbang.qipai.admin.plan.service.ReportService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
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
	private ReportService reportService;

	@Autowired
	private MemberDboService memberService;

	@Autowired
	private MemberOrderService orderService;

	/**
	 * 平台运营数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/platformreport")
	public CommonVO platformOperateReport(@RequestParam(required = true) Long startTime,
			@RequestParam(required = true) Long endTime, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		CommonVO vo = new CommonVO();
		ListPage listPage = reportService.platformReport(page, size, startTime, endTime);
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
	@RequestMapping("/gamereport")
	public CommonVO gameReport(@RequestParam(required = true) Long startTime,
			@RequestParam(required = true) Long endTime, @RequestParam(required = true) String game) {
		CommonVO vo = new CommonVO();
		List<GameDataReport> reportList = reportService.gameReport(startTime, endTime, game);
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
		long now = System.currentTimeMillis();
		long day = 60 * 60 * 24 * 1000;
		// 当日凌晨
		long startTime = now - (now + 8 * 3600 * 1000) % day - day;
		// 次日凌晨
		long endTime = startTime + day;
		PlatformReport report = new PlatformReport();
		report.setDate(startTime);
		report.setNewMember((int) memberService.countNewMemberByTime(startTime, endTime));
		report.setCurrentMember((int) memberService.countVIP());
		report.setCost(orderService.countCostByTime(startTime, endTime));
		report.setGameNum((int) reportService.countGameNumByTime(startTime));
		report.setLoginMember((int) reportService.countLoginMemberByTime(startTime));
		report.setRemainSecond((int) memberService.countRemain(day));
		report.setRemainThird((int) memberService.countRemain(2 * day));
		report.setRemainSeventh((int) memberService.countRemain(6 * day));
		report.setRemainMonth((int) memberService.countRemain(30 * day));
		reportService.addPlatformReport(report);
	}

}
