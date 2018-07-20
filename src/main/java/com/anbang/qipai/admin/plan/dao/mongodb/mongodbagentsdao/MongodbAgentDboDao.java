package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentsdao.AgentDboDao;
import com.anbang.qipai.admin.plan.domain.agents.AgentDbo;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentDboVO;
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
	public List<AgentDbo> findAgentDboByConditions(int page, int size, AgentDboVO agent) {
		Query query = new Query();
		if (agent.getId() != null && !"".equals(agent.getId())) {
			query.addCriteria(Criteria.where("id").is(agent.getId()));
		}
		if (agent.getNickname() != null && !"".equals(agent.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(agent.getNickname()));
		}
		if (agent.getPhone() != null && !"".equals(agent.getPhone())) {
			query.addCriteria(Criteria.where("phone").regex(agent.getPhone()));
		}
		if (agent.getUserName() != null && !"".equals(agent.getUserName())) {
			query.addCriteria(Criteria.where("nickname").regex(agent.getUserName()));
		}
		if (agent.getLevel() != null) {
			query.addCriteria(Criteria.where("level").is(agent.getLevel()));
		}
		if (agent.getStartTime() != null || agent.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (agent.getStartTime() != null) {
				criteria = criteria.gte(agent.getStartTime());
			}
			if (agent.getEndTime() != null) {
				criteria = criteria.lte(agent.getEndTime());
			}
			query.addCriteria(criteria);
		}
		query.addCriteria(Criteria.where("agentAuth").is(true));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentDbo.class);
	}

	@Override
	public boolean updateAgentDboBoss(String agentId, String bossId, String bossName) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("bossId", bossId);
		update.set("bossName", bossName);
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
	public long getAmountByConditions(AgentDboVO agent) {
		Query query = new Query();
		if (agent.getId() != null && !"".equals(agent.getId())) {
			query.addCriteria(Criteria.where("id").is(agent.getId()));
		}
		if (agent.getNickname() != null && !"".equals(agent.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(agent.getNickname()));
		}
		if (agent.getPhone() != null && !"".equals(agent.getPhone())) {
			query.addCriteria(Criteria.where("phone").regex(agent.getPhone()));
		}
		if (agent.getUserName() != null && !"".equals(agent.getUserName())) {
			query.addCriteria(Criteria.where("nickname").regex(agent.getUserName()));
		}
		if (agent.getLevel() != null) {
			query.addCriteria(Criteria.where("level").is(agent.getLevel()));
		}
		if (agent.getStartTime() != null || agent.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (agent.getStartTime() != null) {
				criteria = criteria.gte(agent.getStartTime());
			}
			if (agent.getEndTime() != null) {
				criteria = criteria.lte(agent.getEndTime());
			}
			query.addCriteria(criteria);
		}
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

	@Override
	public boolean updateAgentDboState(String agentId, String state) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.set("state", state);
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}

	@Override
	public AgentDbo findAgentDboById(String agentId) {
		Query query = new Query(Criteria.where("id").is(agentId));
		return mongoTemplate.findOne(query, AgentDbo.class);
	}

	@Override
	public boolean removeAgentDboBoss(String agentId) {
		Query query = new Query(Criteria.where("id").is(agentId));
		Update update = new Update();
		update.unset("bossId");
		update.unset("bossName");
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentDbo.class);
		return result.getN() > 0;
	}
}
