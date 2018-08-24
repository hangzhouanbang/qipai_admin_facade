package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentOrder;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentOrderDao;

@Component
public class MongodbAgentOrderDao implements AgentOrderDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addOrder(AgentOrder order) {
		mongoTemplate.insert(order);
	}

	@Override
	public void orderFinished(String id, String transaction_id, String status, long deliveTime) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("transaction_id", transaction_id);
		update.set("status", status);
		update.set("deliveTime", deliveTime);
		mongoTemplate.updateFirst(query, update, AgentOrder.class);
	}

}
