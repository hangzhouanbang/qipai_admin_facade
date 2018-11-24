package com.anbang.qipai.admin.plan.dao.mongodb.mongodbreportdao;

import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;
import com.anbang.qipai.admin.plan.dao.reportdao.OnlineStateRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
@Repository
public class MongodbOnlineStateRecordDao implements OnlineStateRecordDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(OnlineStateRecord onlineStateRecord) {
        mongoTemplate.insert(onlineStateRecord);
    }
}
