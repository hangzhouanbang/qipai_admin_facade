package com.anbang.qipai.admin.plan.dao.reportdao;

import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
public interface OnlineStateRecordDao {
    /**
     * 新增一条上下线记录
     * @param onlineStateRecord
     */
    void insert(OnlineStateRecord onlineStateRecord);
}
