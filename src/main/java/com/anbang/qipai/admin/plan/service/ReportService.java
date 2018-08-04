package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.GameDataReport;
import com.anbang.qipai.admin.plan.bean.PlatformReport;
import com.anbang.qipai.admin.plan.dao.GameReportDao;
import com.anbang.qipai.admin.plan.dao.PlatformReportDao;
import com.highto.framework.web.page.ListPage;

@Service
public class ReportService {

	@Autowired
	private PlatformReportDao platformReportDao;

	@Autowired
	private GameReportDao gameReportDao;

	public ListPage platformReport(int page, int size, long startTime, long endTime) {
		long amount = platformReportDao.getAmountByTime(startTime, endTime);
		List<PlatformReport> reportList = platformReportDao.findReportByTime(page, size, startTime, endTime);
		ListPage listPage = new ListPage(reportList, page, size, (int) amount);
		return listPage;
	}

	public List<GameDataReport> gameReport(long startTime, long endTime, String game) {
		List<GameDataReport> reportList = gameReportDao.findReportByTime(startTime, endTime, game);
		return reportList;
	}

	public void addPlatformReport(PlatformReport report) {
		platformReportDao.addReport(report);
	}

	public void addGameReport(GameDataReport report) {
		gameReportDao.addReport(report);
	}

	public long countGameNumByTime(long date) {
		return gameReportDao.countGameNumByTime(date);
	}

	public long countLoginMemberByTime(long date) {
		return gameReportDao.countLoginMemberByTime(date);
	}
}
