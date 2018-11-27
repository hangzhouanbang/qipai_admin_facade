package com.anbang.qipai.admin.plan.service.reportservice;

import com.anbang.qipai.admin.plan.bean.report.BasicDataReport;
import com.anbang.qipai.admin.plan.bean.report.DetailedReport;
import com.anbang.qipai.admin.plan.dao.reportdao.DetailedReportDao;
import com.anbang.qipai.admin.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YaphetS
 * @date 2018/11/26
 */
@Service
public class DetailedReportService {

    @Autowired
    private DetailedReportDao reportDao;

    public void upsert(DetailedReport detailedReport) {
        reportDao.upsert(detailedReport);
    }


    public DetailedReport findByCreateTime(long dayStartTime) {
        return reportDao.findByCreateTime(dayStartTime);
    }

    public void updateByOnline(DetailedReport detailedReport) {
        reportDao.updateByOnline(detailedReport);
    }


    public void updateDetailedReport(BasicDataReport basicDataReport) {
        //最高在线修改后,修改明细表(最终生成的数据表)
        //根据时间(日期开始时间)查明细表
        //得到当前日子的开始时间(以天为精度的时间戳)
        long dayStartTime= TimeUtil.getTimeWithDayPrecision(basicDataReport.getCreateTime());
        DetailedReport detailed=findByCreateTime(dayStartTime);
        if(detailed==null){
            //不存在:upsert:添加数据(当前日期(峰值时间),同时在线,峰值时间)
            DetailedReport detailedReport=new DetailedReport();
            detailedReport.setCreateTime(dayStartTime);
            detailedReport.setOnlineCount(basicDataReport.getCurrentQuantity());
            detailedReport.setMaxOnlineTime(basicDataReport.getCreateTime());
            upsert(detailedReport);
        }else{
            //存在
            //如果刚才得到的当前在线>明细表中存在的同时在线
            //update:(同时在线,峰值时间)
            if(basicDataReport.getCurrentQuantity()>detailed.getOnlineCount()){
                updateByOnline(
                        new DetailedReport(dayStartTime,basicDataReport.getCurrentQuantity(),basicDataReport.getCreateTime()));
            }
        }
    }

    public List<DetailedReport> findByTime(Long startTime, Long endTime) {
        return reportDao.findByTime(startTime, endTime);
    }
}
