package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentClubCardRecordDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentClubCardRecordDbo;

@Component
public class MongodbAgentClubCardRecordDboDao implements AgentClubCardRecordDboDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentClubCardRecordDbo(AgentClubCardRecordDbo dbo) {
		mongoTemplate.insert(dbo);
	}

	@Override
	public long getAmountByConditions(AgentClubCardRecordDbo dbo) {
		Query query = new Query();
		if (dbo.getAgentId() != null && !"".equals(dbo.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(dbo.getAgentId()));
		}
		return mongoTemplate.count(query, AgentClubCardRecordDbo.class);
	}

	@Override
	public List<AgentClubCardRecordDbo> findAgentClubCardRecordDboByConditions(int page, int size,
			AgentClubCardRecordDbo dbo) {
		Query query = new Query();
		if (dbo.getAgentId() != null && !"".equals(dbo.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(dbo.getAgentId()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentClubCardRecordDbo.class);
	}

}
