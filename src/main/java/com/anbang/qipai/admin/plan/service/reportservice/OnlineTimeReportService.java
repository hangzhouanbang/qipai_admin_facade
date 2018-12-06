package com.anbang.qipai.admin.plan.service.reportservice;

import com.anbang.qipai.admin.plan.bean.report.OnlineTimeReport;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbreportdao.MongodbOnlineTimeReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YaphetS
 * @date 2018/12/5
 */
@Service
public class OnlineTimeReportService {

    @Autowired
    private MongodbOnlineTimeReportDao reportDao;

    /**
     * 确保创建时间与用户id的组合唯一,更新该日在线时长
     * @param report
     */
    public void upsertReport(OnlineTimeReport report) {
        reportDao.upsertByCreateTimeAndMemberId(report);
    }


    public List<OnlineTimeReport> findByTime(Long createTime) {
        return reportDao.findByTime(createTime);
    }
}
