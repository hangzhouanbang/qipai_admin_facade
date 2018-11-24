package com.anbang.qipai.admin.plan.dao.reportdao;

import com.anbang.qipai.admin.plan.bean.report.BasicDataReport;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
public interface BasicDataReportDao {
    /**
     * 查找是否已经增加过一条以小时为精度的平均在线时长数据
     * @param createTime
     * @return
     */
    BasicDataReport findByCreateTime(long createTime);

    /**
     * 新增一条以小时为精度的平均在线时长数据
     * @param basicDataReport
     */
    void insert(BasicDataReport basicDataReport);

    /**
     * 当前在线字段自增或自减
     * @param basicDataReport
     */
    void incCurrentQuantity(BasicDataReport basicDataReport,int modify);

    void updateMaxQuantity(BasicDataReport basicDataReport);
}
