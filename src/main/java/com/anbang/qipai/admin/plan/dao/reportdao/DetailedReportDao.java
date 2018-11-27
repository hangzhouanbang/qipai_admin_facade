package com.anbang.qipai.admin.plan.dao.reportdao;

import com.anbang.qipai.admin.plan.bean.report.DetailedReport;

import java.util.List;

/**
 * @author YaphetS
 * @date 2018/11/26
 */
public interface DetailedReportDao {

    /**
     * 更新明细表
     * @param detailedReport
     */
    void upsert(DetailedReport detailedReport);

    /**
     * 查询明细表是否有当天的数据
     * @param dayStartTime
     * @return
     */
    DetailedReport findByCreateTime(long dayStartTime);

    /**
     * 更新明细表的在线人数明细数据
     * @param detailedReport
     */
    void updateByOnline(DetailedReport detailedReport);

    /**
     * 给定开始时间和结束时间,查询明细记录
     * @param startTime
     * @param endTime
     * @return
     */
    List<DetailedReport> findByTime(Long startTime, Long endTime);
}
