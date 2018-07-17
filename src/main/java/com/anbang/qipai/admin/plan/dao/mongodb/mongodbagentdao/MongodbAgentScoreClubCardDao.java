package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentScoreClubCardDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreClubCard;
import com.mongodb.WriteResult;

@Component
public class MongodbAgentScoreClubCardDao implements AgentScoreClubCardDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addScoreClubCard(AgentScoreClubCard card) {
		mongoTemplate.insert(card);
	}

	@Override
	public boolean deleteScoreClubCard(String[] cardIds) {
		Object[] ids = cardIds;
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult reslut = mongoTemplate.remove(query, AgentScoreClubCard.class);
		return reslut.getN() <= cardIds.length;
	}

	@Override
	public boolean updateScoreById(String id, int score) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("score", score);
		WriteResult reslut = mongoTemplate.updateFirst(query, update, AgentScoreClubCard.class);
		return reslut.getN() > 0;
	}

	@Override
	public boolean updateNumberById(String id, int number) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("number", number);
		WriteResult reslut = mongoTemplate.updateFirst(query, update, AgentScoreClubCard.class);
		return reslut.getN() > 0;
	}

	@Override
	public List<AgentScoreClubCard> findScoreClubCard(int page, int size) {
		Query query = new Query();
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, AgentScoreClubCard.class);
	}

	@Override
	public long getAmount() {
		Query query = new Query();
		return mongoTemplate.count(query, AgentScoreClubCard.class);
	}

}
