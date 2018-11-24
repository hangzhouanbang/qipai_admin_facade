package com.anbang.qipai.admin.plan.dao.mongodb.mongodbreportdao;

import com.anbang.qipai.admin.plan.bean.report.BasicDataReport;
import com.anbang.qipai.admin.plan.dao.reportdao.BasicDataReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
@Repository
public class MongodbBasicDataReportDao implements BasicDataReportDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public BasicDataReport findByCreateTime(long createTime) {
        Query query = new Query(Criteria.where("createTime").is(createTime));
        return mongoTemplate.findOne(query,BasicDataReport.class);
    }

    @Override
    public void insert(BasicDataReport basicDataReport) {
        mongoTemplate.insert(basicDataReport);
    }

    @Override
    public void incCurrentQuantity(BasicDataReport basicDataReport,int modify) {

        Query query=new Query(Criteria.where("id").is(basicDataReport.getId()));
        Update update=new Update().inc("currentQuantity",modify);
        mongoTemplate.updateFirst(query,update,BasicDataReport.class);
    }

    @Override
    public void updateMaxQuantity(BasicDataReport basicDataReport) {
        Query query=new Query(Criteria.where("id").is(basicDataReport.getId()));
        Update update=new Update().set("maxQuantity",basicDataReport.getMaxQuantity());
        mongoTemplate.updateFirst(query,update,BasicDataReport.class);
    }
}
