package com.anbang.qipai.admin.plan.dao.mongodb.mongodbrecorddao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.recorddao.OrderDao;
import com.anbang.qipai.admin.plan.domain.record.Order;
import com.anbang.qipai.admin.web.vo.OrderVO;
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
	public Boolean updateOrder(Order order) {
		Query query = new Query(Criteria.where("out_trade_no").is(order.getOut_trade_no()));
		Update update = new Update();
		if (order.getStatus() != null) {
			update.set("status", order.getStatus());
		}
		if (order.getDeliveTime() != null) {
			update.set("deliveTime", order.getDeliveTime());
		}
		if (order.getTransaction_id() != null) {
			update.set("transaction_id", order.getTransaction_id());
		}
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Order.class);
		return writeResult.getN() > 0;
	}

	@Override
	public List<Order> findOrder(int page, int size, Sort sort, OrderVO order) {
		Query query = new Query();
		if (order.getOut_trade_no() != null && order.getOut_trade_no() != "") {
			query.addCriteria(Criteria.where("out_trade_no").is(order.getOut_trade_no()));
		}
		if (order.getPay_type() != null && order.getPay_type() != "") {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getMemberId() != null) {
			query.addCriteria(Criteria.where("memberId").is(order.getMemberId()));
		}
		if (order.getStatus() != null) {
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
		if (order.getNickname() != null) {
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
		query.with(sort);
		return mongoTemplate.find(query, Order.class);
	}

	@Override
	public Order findOrderByOut_trade_no(String out_trade_no) {
		Query query = new Query(Criteria.where("out_trade_no").is(out_trade_no));
		return mongoTemplate.findOne(query, Order.class);
	}

	@Override
	public long getAmount(OrderVO order) {
		Query query = new Query();
		if (order.getOut_trade_no() != null && order.getOut_trade_no() != "") {
			query.addCriteria(Criteria.where("out_trade_no").is(order.getOut_trade_no()));
		}
		if (order.getPay_type() != null && order.getPay_type() != "") {
			query.addCriteria(Criteria.where("pay_type").is(order.getPay_type()));
		}
		if (order.getMemberId() != null) {
			query.addCriteria(Criteria.where("memberId").is(order.getMemberId()));
		}
		if (order.getStatus() != null) {
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
		if (order.getNickname() != null) {
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

}
