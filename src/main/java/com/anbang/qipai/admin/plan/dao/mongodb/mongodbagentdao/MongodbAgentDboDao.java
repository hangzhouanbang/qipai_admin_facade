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
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentDbo(AgentDbo agent) {
		mongoTemplate.insert(agent);
	}

	@Override
	public List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDbo agent) {
		Query query = new Query(Criteria.where("level").is(agent.getLevel()));
		query.addCriteria(Criteria.where("agentAuth").is(true));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentDbo.class);
	}

	@Override
	public boolean updateAgentDboBossId(String agentId, String bossId) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("bossId", bossId);
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public boolean updateAgentDboLevel(String agentId, int level) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("level", level);
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public long getAmountByConditions(AgentDbo agent) {
		Query query = new Query(Criteria.where("level").is(agent.getLevel()));
		query.addCriteria(Criteria.where("agentAuth").is(true));
		return mongoTemplate.count(query, AgentDbo.class);
	}

	@Override
	public boolean updateAgnetInfo(String agentId, String phone, String userName, String idCard, String frontUrl,
			String reverseUrl) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("phone", phone);
		update.set("userName", userName);
		update.set("idCard", idCard);
		update.set("frontUrl", frontUrl);
		update.set("reverseUrl", reverseUrl);
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public boolean updateAgentAuth(String agentId, boolean agentAuth) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("agentAuth", agentAuth);
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

}
