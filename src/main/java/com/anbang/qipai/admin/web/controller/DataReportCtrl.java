package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.PlatformReport;
import com.anbang.qipai.admin.plan.service.MemberService;
import com.anbang.qipai.admin.plan.service.OrderService;
import com.anbang.qipai.admin.plan.service.ReportService;
import com.anbang.qipai.admin.web.vo.CommonVO;

@RestController
@RequestMapping("/datareport")
public class DataReportCtrl {

	@Autowired
	private ReportService reportService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/platform")
	public CommonVO platformOperateReport(Long startTime, Long endTime) {
		CommonVO vo = new CommonVO();
		List<PlatformReport> reportList = reportService.dataReport(startTime, endTime);
		vo.setSuccess(true);
		vo.setMsg("reportList");
		vo.setData(reportList);
		return vo;
	}

	public void createPlatformReport() {
		long now = System.currentTimeMillis();
		long day = 60 * 60 * 24 * 1000;
		long startTime = now - (now + 8 * 3600 * 1000) % day;
		long endTime = startTime + day;
		PlatformReport report = new PlatformReport();
		report.setDate(startTime);
		report.setNewMember(0);
		report.setCurrentMember(0);
		report.setCost(0.0);
		report.setGameNum(0);
		report.setLoginMember(0);
		report.setRemainSecond(0);
		report.setRemainThird(0);
		report.setRemainSeventh(0);
		report.setRemainMonth(0);
		reportService.addReport(report);
	}
}
