package com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanYushiRecordDboDao;
import com.anbang.qipai.admin.plan.dao.mongodb.mongodbchaguandao.repository.ChaguanYushiRecordDboRepository;
import com.anbang.qipai.admin.web.vo.chaguanvo.ChaguanYushiRecordDboVO;

@Component
public class MongodbChaguanYushiRecordDboDao implements ChaguanYushiRecordDboDao {

	@Autowired
	private ChaguanYushiRecordDboRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(ChaguanYushiRecordDbo dbo) {
		repository.save(dbo);
	}

	@Override
	public long countByConditions(ChaguanYushiRecordDboVO record) {
		Query query = new Query();
		if (record.getId() != null && !"".equals(record.getId())) {
			query.addCriteria(Criteria.where("id").is(record.getId()));
		}
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("payerId").is(record.getAgentId()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		return mongoTemplate.count(query, ChaguanYushiRecordDbo.class);
	}

	@Override
	public List<ChaguanYushiRecordDbo> findByConditions(int page, int size, ChaguanYushiRecordDboVO record) {
		Query query = new Query();
		if (record.getId() != null && !"".equals(record.getId())) {
			query.addCriteria(Criteria.where("id").is(record.getId()));
		}
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("payerId").is(record.getAgentId()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		// 需要建立索引
		query.with(record.getSort());
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, ChaguanYushiRecordDbo.class);
	}

}
