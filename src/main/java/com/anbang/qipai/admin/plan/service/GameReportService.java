package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.report.GameDataReport;
import com.anbang.qipai.admin.plan.dao.GameReportDao;

@Service
public class GameReportService {

	@Autowired
	private GameReportDao gameReportDao;

	public List<GameDataReport> findGameReportByTimeAndGame(long startTime, long endTime, String game) {
		List<GameDataReport> reportList = gameReportDao.findReportByTime(startTime, endTime, game);
		return reportList;
	}

	public void addGameReport(GameDataReport report) {
		gameReportDao.addReport(report);
	}

	public int countGameNumByTime(long startTime, long endTime) {
		return gameReportDao.countGameNumByTime(startTime, endTime);
	}
}
