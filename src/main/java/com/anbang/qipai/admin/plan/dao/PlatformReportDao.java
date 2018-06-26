package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.PlatformReport;

public interface PlatformReportDao {

	List<PlatformReport> findReportByTime(long startTime, long endTime);

	void addReport(PlatformReport report);

	void updateReport(PlatformReport report);
}
