package com.anbang.qipai.admin.publisher.notifier;

import com.anbang.qipai.admin.plan.bean.report.BasicDataReport;
import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;
import com.anbang.qipai.admin.plan.dao.reportdao.BasicDataReportDao;
import com.anbang.qipai.admin.plan.dao.reportdao.OnlineStateRecordDao;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.publisher.EntityCreatedEvent;
import com.anbang.qipai.admin.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
@Component
public class OnlineRecordNotifier {
    private final static int ON_LINE_ACTION=1;
    private final static int OFF_LINE_ACTION=-1;

    @Autowired
    private OnlineStateRecordDao onlineStateRecordDao;

    @Autowired
    private BasicDataReportDao basicDataReportDao;

    @Autowired
    private MemberDboService memberDboService;

    @EventListener
    @Async
    public void processContentEvent(EntityCreatedEvent<OnlineStateRecord> event) {

        OnlineStateRecord onlineStateRecord=(OnlineStateRecord)event.getSource();
        //添加上下线记录
        onlineStateRecordDao.insert(onlineStateRecord);

        //得到当前点钟的开始时间(以小时为精度的时间戳)
        long createTime= TimeUtil.getTimeWithHourPrecision(onlineStateRecord.getCreateTime());
        BasicDataReport report=basicDataReportDao.findByCreateTime(createTime);
        if(report==null){
            //当前时间的记录不存在
            //在线人数
            Integer onlineCount=(int)memberDboService.countOnlineState();
            //（开始时间，当前在线，最高在线（跟上个字段数据一致））
            basicDataReportDao.insert(new BasicDataReport(createTime,onlineCount,onlineCount));
        }else{
            //当前时间的记录存在
            Integer onlineState=onlineStateRecord.getOnlineState();

            if(onlineState==OnlineStateRecord.ON_LINE){
                //$inc,当前在线+1
                basicDataReportDao.incCurrentQuantity(report,ON_LINE_ACTION);
                //为了防止并发,根据开始时间查出这条数据,如果当前在线>最高在线,最高在线=当前在线,存入数据库
                BasicDataReport basicDataReport=basicDataReportDao.findByCreateTime(createTime);
                if(basicDataReport.getCurrentQuantity()>basicDataReport.getMaxQuantity()){
                    basicDataReport.setMaxQuantity(basicDataReport.getCurrentQuantity());
                    basicDataReportDao.updateMaxQuantity(basicDataReport);
                }

            }else if(onlineState==OnlineStateRecord.OFF_LINE){
                //$inc,当前在线-1
                basicDataReportDao.incCurrentQuantity(report,OFF_LINE_ACTION);
            }
        }
    }
}
