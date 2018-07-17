package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentCostClubCardDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentCostClubCard;
import com.mongodb.WriteResult;

@Component
public class MongoAgentCostClubCardDao implements AgentCostClubCardDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addCostClubCard(AgentCostClubCard card) {
		mongoTemplate.insert(card);
	}

	@Override
	public boolean deleteCostClubCard(String[] cardIds) {
		Object[] ids = cardIds;
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult reslut = mongoTemplate.remove(query, AgentCostClubCard.class);
		return reslut.getN() <= cardIds.length;
	}

	@Override
	public boolean updateCostById(String id, int cost) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("cost", cost);
		WriteResult reslut = mongoTemplate.updateFirst(query, update, AgentCostClubCard.class);
		return reslut.getN() > 0;
	}

	@Override
	public boolean updateNumberById(String id, int number) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("number", number);
		WriteResult reslut = mongoTemplate.updateFirst(query, update, AgentCostClubCard.class);
		return reslut.getN() > 0;
	}

	@Override
	public List<AgentCostClubCard> findCostClubCard(int page, int size) {
		Query query = new Query();
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentCostClubCard.class);
	}

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, AgentCostClubCard.class);
	}

}
