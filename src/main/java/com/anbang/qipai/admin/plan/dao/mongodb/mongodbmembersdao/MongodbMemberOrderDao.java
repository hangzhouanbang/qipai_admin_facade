package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberOrder;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberOrderDao;
import com.anbang.qipai.admin.web.vo.membersvo.MemberOrderVO;
import com.mongodb.BasicDBObject;

@Component
public class MongodbMemberOrderDao implements MemberOrderDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addOrder(MemberOrder order) {
		mongoTemplate.insert(order);
	}

	@Override
	public List<MemberOrder> findOrderByConditions(int page, int size, MemberOrderVO order) {
		Query query = new Query();
		if (order.getId() != null && !"".equals(order.getId())) {
			query.addCriteria(Criteria.where("id").is(order.getId()));
		}
		if (order.getPay_type() != null && !"".equals(order.getPay_type())) {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getPayerId() != null && !"".equals(order.getPayerId())) {
			query.addCriteria(Criteria.where("payerId").is(order.getPayerId()));
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
		if (order.getPayerName() != null && !"".equals(order.getPayerName())) {
			query.addCriteria(Criteria.where("payerName").regex(order.getPayerName()));
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
		// 需要建立索引
		query.with(new Sort(new Order(Direction.DESC, "createTime")));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, MemberOrder.class);
	}

	@Override
	public long getAmountByConditions(MemberOrderVO order) {
		Query query = new Query();
		if (order.getId() != null && !"".equals(order.getId())) {
			query.addCriteria(Criteria.where("id").is(order.getId()));
		}
		if (order.getPay_type() != null && !"".equals(order.getPay_type())) {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getPayerId() != null && !"".equals(order.getPayerId())) {
			query.addCriteria(Criteria.where("payerId").is(order.getPayerId()));
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
		if (order.getPayerName() != null && !"".equals(order.getPayerName())) {
			query.addCriteria(Criteria.where("payerName").regex(order.getPayerName()));
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
		return mongoTemplate.count(query, MemberOrder.class);
	}

	@Override
	public double countCostByTime(long startTime, long endTime) {
		Aggregation aggregation = Aggregation.newAggregation(MemberOrder.class,
				Aggregation.match(Criteria.where("createTime").gte(startTime).lte(endTime).and("status")
						.in("TRADE_SUCCESS", "TRADE_FINISHED", "SUCCESS")),
				Aggregation.group().sum("totalamount").as("cost"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, MemberOrder.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getDouble("cost");
	}

	@Override
	public void orderFinished(String id, String transaction_id, String status, long deliveTime) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("transaction_id", transaction_id);
		update.set("status", status);
		update.set("deliveTime", deliveTime);
		mongoTemplate.updateFirst(query, update, MemberOrder.class);
	}

	@Override
	public double countCostByMemberId(String memberId) {
		Aggregation aggregation = Aggregation.newAggregation(
				MemberOrder.class, Aggregation.match(Criteria.where("payerId").is(memberId).and("status")
						.in("TRADE_SUCCESS", "TRADE_FINISHED", "SUCCESS")),
				Aggregation.group().sum("totalamount").as("cost"));
		AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, MemberOrder.class,
				BasicDBObject.class);
		List<BasicDBObject> list = result.getMappedResults();
		if (list.isEmpty()) {
			return 0;
		}
		BasicDBObject basicObj = list.get(0);
		return basicObj.getDouble("cost");
	}

}
