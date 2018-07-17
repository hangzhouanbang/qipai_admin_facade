package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentScoreRecordDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreRecordDbo;

@Component
public class MongodbAgentScoreRecordDboDao implements AgentScoreRecordDboDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentScoreRecordDbo(AgentScoreRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

	@Override
	public long getAmountByConditions(AgentScoreRecordDbo dbo) {
		Query query = new Query();
		if (dbo.getAgentId() != null && !"".equals(dbo.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(dbo.getAgentId()));
		}
		return mongoTemplate.count(query, AgentScoreRecordDbo.class);
	}

	@Override
	public List<AgentScoreRecordDbo> findAgentScoreRecordDboByConditions(int page, int size, AgentScoreRecordDbo dbo) {
		Query query = new Query();
		if (dbo.getAgentId() != null && !"".equals(dbo.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(dbo.getAgentId()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentScoreRecordDbo.class);
	}

}
