package com.anbang.qipai.admin.plan.service.reportservice;

import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;
import com.anbang.qipai.admin.plan.dao.reportdao.OnlineStateRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YaphetS
 * @date 2018/11/26
 */
@Service
public class OnlineStateRecordService {

    @Autowired
    private OnlineStateRecordDao onlineStateRecordDao;


    public Long countOnlineRecord() {
        return onlineStateRecordDao.countOnlineRecord();
    }

    public Integer countOnlineRecordAfterTime(long createTime){
        return (int)onlineStateRecordDao.countOnlineRecordAfterTime(createTime);
    }

    public void insert(OnlineStateRecord onlineStateRecord) {
        onlineStateRecordDao.insert(onlineStateRecord);
    }
}
