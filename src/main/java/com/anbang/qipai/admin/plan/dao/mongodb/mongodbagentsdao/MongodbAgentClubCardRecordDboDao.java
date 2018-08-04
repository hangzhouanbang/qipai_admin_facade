package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentClubCardRecordDbo;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentClubCardRecordDboDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentClubCardRecordDboVO;

@Component
public class MongodbAgentClubCardRecordDboDao implements AgentClubCardRecordDboDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentClubCardRecordDbo(AgentClubCardRecordDbo record) {
		mongoTemplate.insert(record);
	}

	@Override
	public long getAmountByConditions(AgentClubCardRecordDboVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getAgent() != null && !"".equals(record.getAgent())) {
			query.addCriteria(Criteria.where("agent").regex(record.getAgent()));
		}
		if (record.getType() != null && !"".equals(record.getType())) {
			query.addCriteria(Criteria.where("summary.text").regex(record.getType()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("accountingTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		return mongoTemplate.count(query, AgentClubCardRecordDbo.class);
	}

	@Override
	public List<AgentClubCardRecordDbo> findAgentClubCardRecordDboByConditions(int page, int size,
			AgentClubCardRecordDboVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getAgent() != null && !"".equals(record.getAgent())) {
			query.addCriteria(Criteria.where("agent").regex(record.getAgent()));
		}
		if (record.getType() != null && !"".equals(record.getType())) {
			query.addCriteria(Criteria.where("summary.text").regex(record.getType()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("accountingTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentClubCardRecordDbo.class);
	}

}
