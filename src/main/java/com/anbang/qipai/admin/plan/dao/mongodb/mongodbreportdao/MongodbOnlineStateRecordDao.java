package com.anbang.qipai.admin.plan.dao.mongodb.mongodbreportdao;

import com.anbang.qipai.admin.plan.bean.report.OnlineStateRecord;
import com.anbang.qipai.admin.plan.dao.reportdao.OnlineStateRecordDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public long countOnlineRecord() {
        Query query = new Query(Criteria.where("onlineState").is(0));
        return mongoTemplate.count(query, OnlineStateRecord.class);
    }

    @Override
    public long countOnlineRecordAfterTime(long createTime) {
        BasicDBObject query = new BasicDBObject();
        query.put("createTime", new BasicDBObject("$gte", createTime));
        query.put("onlineState",0);
        return mongoTemplate.getCollection("onlineStateRecord").distinct("memberId", query).size();
    }

    @Override
    public List<OnlineStateRecord> findOnlineRecordAfterTime(long dayStartTime) {
        Query query=new Query(Criteria.where("createTime").gte(dayStartTime));
        return mongoTemplate.find(query,OnlineStateRecord.class);
    }

}
