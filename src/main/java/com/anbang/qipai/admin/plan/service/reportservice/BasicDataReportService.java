package com.anbang.qipai.admin.plan.service.reportservice;

import com.anbang.qipai.admin.plan.bean.report.BasicDataReport;
import com.anbang.qipai.admin.plan.dao.reportdao.BasicDataReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YaphetS
 * @date 2018/11/26
 */
@Service
public class BasicDataReportService {

    @Autowired
    private BasicDataReportDao reportDao;

    public List<BasicDataReport> findBasicDataAfterTime(long startTime) {
        return reportDao.findBasicDataAfterTime(startTime);
    }
}
