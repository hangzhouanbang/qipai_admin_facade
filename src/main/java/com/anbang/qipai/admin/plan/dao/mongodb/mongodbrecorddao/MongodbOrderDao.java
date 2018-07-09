package com.anbang.qipai.admin.plan.dao.mongodb.mongodbrecorddao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.recorddao.OrderDao;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.web.vo.OrderVO;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

@Component
public class MongodbOrderDao implements OrderDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addOrder(Order order) {
		mongoTemplate.insert(order);
	}

	@Override
	public List<Order> findOrderByConditions(int page, int size, OrderVO order) {
		Query query = new Query();
		if (order.getOut_trade_no() != null && !"".equals(order.getOut_trade_no())) {
			query.addCriteria(Criteria.where("out_trade_no").is(order.getOut_trade_no()));
		}
		if (order.getPay_type() != null && !"".equals(order.getPay_type())) {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getMemberId() != null && !"".equals(order.getMemberId())) {
			query.addCriteria(Criteria.where("memberId").is(order.getMemberId()));
		}
		if (order.getStatus() != null && !"".equals(order.getStatus())) {
			if ("0".equals(order.getStatus())) {
				query.addCriteria(Criteria.where("status").in("WAIT_BUYER_PAY", "USERPAYING"));
			}
			if ("-1".equals(order.getStatus())) {
				query.addCriteria(
						Criteria.where("status").in("TRADE_CLOSED", "CLOSED", "REFUND", "REVOKED", "PAYERROR"));
			}
			if ("1".equals(order.getStatus())) {
				query.addCriteria(Criteria.where("status").in("TRADE_SUCCESS", "TRADE_FINISHED", "SUCCESS"));
			}
		}
		if (order.getNickname() != null && !"".equals(order.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(order.getNickname()));
		}
		if (order.getStartTime() != null || order.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (order.getStartTime() != null) {
				criteria = criteria.gte(order.getStartTime());
			}
			if (order.getEndTime() != null) {
				criteria = criteria.lte(order.getEndTime());
			}
			query.addCriteria(criteria);
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, Order.class);
	}

	@Override
	public long getAmountByConditions(OrderVO order) {
		Query query = new Query();
		if (order.getOut_trade_no() != null && !"".equals(order.getOut_trade_no())) {
			query.addCriteria(Criteria.where("out_trade_no").is(order.getOut_trade_no()));
		}
		if (order.getPay_type() != null && !"".equals(order.getPay_type())) {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getMemberId() != null && !"".equals(order.getMemberId())) {
			query.addCriteria(Criteria.where("memberId").is(order.getMemberId()));
		}
		if (order.getStatus() != null && !"".equals(order.getStatus())) {
			if ("0".equals(order.getStatus())) {
				query.addCriteria(Criteria.where("status").in("WAIT_BUYER_PAY", "USERPAYING"));
			}
			if ("-1".equals(order.getStatus())) {
				query.addCriteria(
						Criteria.where("status").in("TRADE_CLOSED", "CLOSED", "REFUND", "REVOKED", "PAYERROR"));
			}
			if ("1".equals(order.getStatus())) {
				query.addCriteria(Criteria.where("status").in("TRADE_SUCCESS", "TRADE_FINISHED", "SUCCESS"));
			}
		}
		if (order.getNickname() != null && !"".equals(order.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(order.getNickname()));
		}
		if (order.getStartTime() != null || order.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (order.getStartTime() != null) {
				criteria = criteria.gte(order.getStartTime());
			}
			if (order.getEndTime() != null) {
				criteria = criteria.lte(order.getEndTime());
			}
			query.addCriteria(criteria);
		}
		return mongoTemplate.count(query, Order.class);
	}

	@Override
	public double countCostByTime(long startTime, long endTime) {
		Aggregation aggregation = Aggregation.newAggregation(Order.class,
				Aggregation.match(Criteria.where("createTime").gte(startTime).lte(endTime).and("status")
						.in("TRADE_SUCCESS", "TRADE_FINISHED", "SUCCESS")),
				Aggregation.group().sum("totalamount").as("cost"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, Order.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getDouble("cost");
	}

	@Override
	public boolean updateOrderStatusAndDeliveTime(Order order) {
		Query query = new Query(Criteria.where("id").is(order.getId()));
		Update update = new Update();
		update.set("status", order.getStatus());
		update.set("deliveTime", order.getDeliveTime());
		WriteResult result = mongoTemplate.updateFirst(query, update, Order.class);
		return result.getN() > 0;
	}

}
