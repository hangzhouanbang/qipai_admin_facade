package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentClubCard;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentClubCardDao;
import com.mongodb.WriteResult;

@Component
public class MongodbAgentClubCardDao implements AgentClubCardDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addAgentClubCard(AgentClubCard card) {
		mongoTemplate.insert(card);
	}

	@Override
	public boolean deleteAgentClubCard(String[] cardIds) {
		Object[] ids = cardIds;
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult result = mongoTemplate.remove(query, AgentClubCard.class);
		return result.getN() <= cardIds.length;
	}

	@Override
	public boolean updateAgentClubCard(AgentClubCard card) {
		Query query = new Query(Criteria.where("id").is(card.getId()));
		Update update = new Update();
		update.set("product", card.getProduct());
		update.set("productPic", card.getProductPic());
		update.set("number", card.getNumber());
		update.set("repertory", card.getRepertory());
		update.set("payType", card.getPayType());
		update.set("price", card.getPrice());
		update.set("weight", card.getWeight());
		update.set("sale", card.getSale());
		WriteResult result = mongoTemplate.updateFirst(query, update, AgentClubCard.class);
		return result.getN() > 0;
	}

	@Override
	public List<AgentClubCard> findAgentClubCardByConditions(int page, int size, AgentClubCard card) {
		Query query = new Query();
		if (card.getPayType() != null && !"".equals(card.getPayType())) {
			query.addCriteria(Criteria.where("payType").is(card.getPayType()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		Sort sort = new Sort(new Order(Direction.ASC, "weight"));
		query.with(sort);
		return mongoTemplate.find(query, AgentClubCard.class);
	}

	@Override
	public long getAmountByConditions(AgentClubCard card) {
		Query query = new Query();
		if (card.getPayType() != null && !"".equals(card.getPayType())) {
			query.addCriteria(Criteria.where("payType").is(card.getPayType()));
		}
		return mongoTemplate.count(query, AgentClubCard.class);
	}

}
