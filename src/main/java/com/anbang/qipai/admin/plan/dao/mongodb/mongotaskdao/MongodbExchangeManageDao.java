package com.anbang.qipai.admin.plan.dao.mongodb.mongotaskdao;

import com.anbang.qipai.admin.plan.bean.tasks.ExchangeManage;
import com.anbang.qipai.admin.plan.dao.tasksdao.ExchangeManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yins
 * @Description: TODO
 */
@Component
public class MongodbExchangeManageDao implements ExchangeManageDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addExchangeManage(ExchangeManage exchangeManage) {
        mongoTemplate.insert(exchangeManage);
    }

    @Override
    public void updateExchangeManage(ExchangeManage exchangeManage) {
        Query query = new Query(Criteria.where("id").is(exchangeManage.getId()));
        Update update = new Update();
        update.set("exchangeType", exchangeManage.getExchangeType());
        update.set("exchangeCount", exchangeManage.getExchangeCount());
        update.set("exchangeConsumption", exchangeManage.getExchangeConsumption());
        update.set("itemName", exchangeManage.getItemName());
        mongoTemplate.updateFirst(query, update, ExchangeManage.class);
    }

    @Override
    public void deleteExchangeManage(String[] ids) {
        Query query = new Query(Criteria.where("id").in(ids));
        mongoTemplate.remove(query, ExchangeManage.class);
    }

    @Override
    public ExchangeManage getExchangeManage(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, ExchangeManage.class);
    }

    @Override
    public long countExchangeMange() {
        Query query = new Query();
        return mongoTemplate.count(query, ExchangeManage.class);
    }

    @Override
    public List<ExchangeManage> getExchangeManages(int page, int size) {
        Query query = new Query();
        query.skip((page - 1) * size);
        query.limit(size);
        return mongoTemplate.find(query, ExchangeManage.class);
    }
}
