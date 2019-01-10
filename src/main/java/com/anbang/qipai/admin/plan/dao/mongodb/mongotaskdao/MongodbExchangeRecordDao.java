package com.anbang.qipai.admin.plan.dao.mongodb.mongotaskdao;

import com.anbang.qipai.admin.plan.bean.tasks.ExchangeRecord;
import com.anbang.qipai.admin.plan.dao.tasksdao.ExchangeRecordDao;
import com.anbang.qipai.admin.web.query.TaskCommonQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yins
 * @Description: TODO
 */
@Component
public class MongodbExchangeRecordDao implements ExchangeRecordDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addExchangeRecord(ExchangeRecord exchangeRecord) {
        mongoTemplate.insert(exchangeRecord);
    }

    @Override
    public long countExchangeRecord(TaskCommonQuery taskCommonQuery) {
        Query query = new Query();
        if (taskCommonQuery != null) {
            if (StringUtils.isNotBlank(taskCommonQuery.getPlayerId())) {
                query.addCriteria(Criteria.where("playerId").regex(taskCommonQuery.getPlayerId()));
            }
            if (StringUtils.isNotBlank(taskCommonQuery.getLoginIP())) {
                query.addCriteria(Criteria.where("loginIP").is(taskCommonQuery.getLoginIP()));
            }
            if (taskCommonQuery.getStartTime() != null || taskCommonQuery.getEndTime() != null) {
                Criteria criteria = Criteria.where("exchangeTime");
                if (taskCommonQuery.getStartTime() != null) {
                    criteria = criteria.gte(taskCommonQuery.getStartTime());
                }
                if (taskCommonQuery.getEndTime() != null) {
                    criteria = criteria.lte(taskCommonQuery.getEndTime());
                }
                query.addCriteria(criteria);
            }
        }
        return mongoTemplate.count(query, ExchangeRecord.class);
    }

    @Override
    public List<ExchangeRecord> getExchangeRecords(int page, int size, TaskCommonQuery taskCommonQuery) {
        Query query = new Query();
        if (taskCommonQuery != null) {
            if (StringUtils.isNotBlank(taskCommonQuery.getPlayerId())) {
                query.addCriteria(Criteria.where("playerId").regex(taskCommonQuery.getPlayerId()));
            }
            if (StringUtils.isNotBlank(taskCommonQuery.getLoginIP())) {
                query.addCriteria(Criteria.where("loginIP").is(taskCommonQuery.getLoginIP()));
            }
            if (taskCommonQuery.getStartTime() != null || taskCommonQuery.getEndTime() != null) {
                Criteria criteria = Criteria.where("exchangeTime");
                if (taskCommonQuery.getStartTime() != null) {
                    criteria = criteria.gte(taskCommonQuery.getStartTime());
                }
                if (taskCommonQuery.getEndTime() != null) {
                    criteria = criteria.lte(taskCommonQuery.getEndTime());
                }
                query.addCriteria(criteria);
            }
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "exchangeTime")));
        query.skip((page - 1) * size);
        query.limit(size);
        return mongoTemplate.find(query, ExchangeRecord.class);
    }
}
