package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentDboDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentDbo;
import com.mongodb.WriteResult;

@Component
public class MongodbAgentDboDao implements AgentDboDao {

	@Autowired
	private MongoTemplate mongoTempalte;

	@Override
	public void addAgentDbo(AgentDbo agent) {
		mongoTempalte.insert(agent);
	}

	@Override
	public List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDbo agent) {
		Query query = new Query(Criteria.where("level").is(agent.getLevel()));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTempalte.find(query, AgentDbo.class);
	}

	@Override
	public boolean updateAgentDboBossId(String agentId, String bossId) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("bossId", bossId);
		WriteResult result = mongoTempalte.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public boolean updateAgentDboLevel(String agentId, int level) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("level", level);
		WriteResult result = mongoTempalte.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public long getAmountByConditions(AgentDbo agent) {
		Query query = new Query(Criteria.where("level").is(agent.getLevel()));
		return mongoTempalte.count(query, AgentDbo.class);
	}

}
