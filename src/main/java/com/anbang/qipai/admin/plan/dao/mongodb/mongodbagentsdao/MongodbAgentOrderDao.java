package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentsdao.AgentOrderDao;
import com.anbang.qipai.admin.plan.domain.agents.AgentOrder;
import com.mongodb.WriteResult;

@Component
public class MongodbAgentOrderDao implements AgentOrderDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addOrder(AgentOrder order) {
		mongoTemplate.insert(order);
	}

	@Override
	public Boolean updateOrderStatus(String out_trade_no, String status) {
		Query query = new Query(Criteria.where("out_trade_no").is(out_trade_no));
		Update update = new Update();
		update.set("status", status);
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, AgentOrder.class);
		return writeResult.getN() > 0;
	}

	@Override
	public AgentOrder findOrderByOut_trade_no(String out_trade_no) {
		Query query = new Query(Criteria.where("out_trade_no").is(out_trade_no));
		return mongoTemplate.findOne(query, AgentOrder.class);
	}

	@Override
	public Boolean updateTransaction_id(String out_trade_no, String transaction_id) {
		Query query = new Query(Criteria.where("out_trade_no").is(out_trade_no));
		Update update = new Update();
		update.set("transaction_id", transaction_id);
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, AgentOrder.class);
		return writeResult.getN() > 0;
	}

	@Override
	public Boolean updateDeliveTime(String out_trade_no, Long deliveTime) {
		Query query = new Query(Criteria.where("out_trade_no").is(out_trade_no));
		Update update = new Update();
		update.set("deliveTime", deliveTime);
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, AgentOrder.class);
		return writeResult.getN() > 0;
	}

}
