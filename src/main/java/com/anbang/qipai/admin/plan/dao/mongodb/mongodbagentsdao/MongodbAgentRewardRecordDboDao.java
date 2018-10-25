package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentRewardRecordDbo;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentRewardRecordDboDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentRewardRecordDboVO;

@Component
public class MongodbAgentRewardRecordDboDao implements AgentRewardRecordDboDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentRewardRecordDbo(AgentRewardRecordDbo record) {
		mongoTemplate.insert(record);
	}

	@Override
	public long getAmountByConditions(AgentRewardRecordDboVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getBossId() != null && !"".equals(record.getBossId())) {
			query.addCriteria(Criteria.where("bossId").is(record.getBossId()));
		}
		if (record.getMemberId() != null && !"".equals(record.getMemberId())) {
			query.addCriteria(Criteria.where("memberId").is(record.getMemberId()));
		}
		return mongoTemplate.count(query, AgentRewardRecordDbo.class);
	}

	@Override
	public List<AgentRewardRecordDbo> findAgentRewardRecordDboByConditions(int page, int size,
			AgentRewardRecordDboVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getBossId() != null && !"".equals(record.getBossId())) {
			query.addCriteria(Criteria.where("bossId").is(record.getBossId()));
		}
		if (record.getMemberId() != null && !"".equals(record.getMemberId())) {
			query.addCriteria(Criteria.where("memberId").is(record.getMemberId()));
		}
		query.with(record.getSort());
		query.limit(size);
		query.skip((page - 1) * size);
		return mongoTemplate.find(query, AgentRewardRecordDbo.class);
	}

}
