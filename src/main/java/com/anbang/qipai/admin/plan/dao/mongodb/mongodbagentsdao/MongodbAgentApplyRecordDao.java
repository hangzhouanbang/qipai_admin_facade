package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentApplyRecord;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentApplyRecordDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentApplyRecordVO;
import com.mongodb.WriteResult;

@Component
public class MongodbAgentApplyRecordDao implements AgentApplyRecordDao {

	@Autowired
	private MongoTemplate mongoTempalte;

	@Override
	public void addAgentApplyRecord(AgentApplyRecord record) {
		mongoTempalte.insert(record);
	}

	@Override
	public boolean updateAgentApplyRecordSate(String recordId, String state) {
		Query query = new Query(Criteria.where("id").is(recordId));
		Update update = new Update();
		update.set("state", state);
		WriteResult result = mongoTempalte.updateFirst(query, update, AgentApplyRecord.class);
		return result.getN() > 0;
	}

	@Override
	public List<AgentApplyRecord> findAgentApplyRecordByConditions(int page, int size, AgentApplyRecordVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getNickname() != null && !"".equals(record.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(record.getNickname()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTempalte.find(query, AgentApplyRecord.class);
	}

	@Override
	public long getAmountByConditions(int page, int size, AgentApplyRecordVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getNickname() != null && !"".equals(record.getNickname())) {
			query.addCriteria(Criteria.where("nickname").regex(record.getNickname()));
		}
		if (record.getStartTime() != null || record.getEndTime() != null) {
			Criteria criteria = Criteria.where("createTime");
			if (record.getStartTime() != null) {
				criteria = criteria.gte(record.getStartTime());
			}
			if (record.getEndTime() != null) {
				criteria = criteria.lte(record.getEndTime());
			}
			query.addCriteria(criteria);
		}
		return mongoTempalte.count(query, AgentApplyRecord.class);
	}

	@Override
	public AgentApplyRecord findAgentApplyRecordById(String recordId) {
		Query query = new Query(Criteria.where("id").is(recordId));
		return mongoTempalte.findOne(query, AgentApplyRecord.class);
	}

}
