package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.GameDataReport;

public interface GameReportDao {
	List<GameDataReport> findReportByTime(long startTime, long endTime, String game);

	void addReport(GameDataReport report);

	long countGameNumByTime(long date);

	long countLoginMemberByTime(long date);
}
