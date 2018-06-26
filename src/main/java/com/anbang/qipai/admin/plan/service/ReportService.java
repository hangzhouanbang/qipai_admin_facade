package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.PlatformReportDao;
import com.anbang.qipai.admin.plan.domain.PlatformReport;

@Service
public class ReportService {

	@Autowired
	private PlatformReportDao platformReportDao;

	public List<PlatformReport> dataReport(long startTime, long endTime) {
		List<PlatformReport> reportList = platformReportDao.findReportByTime(startTime, endTime);
		return reportList;
	}

	public void addReport(PlatformReport report) {
		platformReportDao.addReport(report);
	}

	public void updateReport(PlatformReport report) {
		platformReportDao.updateReport(report);
	}
}
