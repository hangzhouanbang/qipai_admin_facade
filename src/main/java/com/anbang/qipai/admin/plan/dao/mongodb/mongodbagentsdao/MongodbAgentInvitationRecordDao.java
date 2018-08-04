package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentsdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.agents.AgentInvitationRecord;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentInvitationRecordDao;
import com.anbang.qipai.admin.web.vo.agentsvo.AgentInvitationRecordVO;

@Component
public class MongodbAgentInvitationRecordDao implements AgentInvitationRecordDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addInvitationRecord(AgentInvitationRecord record) {
		mongoTemplate.insert(record);
	}

	@Override
	public List<AgentInvitationRecord> findInvitationRecordByConditions(int page, int size,
			AgentInvitationRecordVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getAgent() != null && !"".equals(record.getAgent())) {
			query.addCriteria(Criteria.where("agent").regex(record.getAgent()));
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
		return mongoTemplate.find(query, AgentInvitationRecord.class);
	}

	@Override
	public long getAmountByByConditions(AgentInvitationRecordVO record) {
		Query query = new Query();
		if (record.getAgentId() != null && !"".equals(record.getAgentId())) {
			query.addCriteria(Criteria.where("agentId").is(record.getAgentId()));
		}
		if (record.getAgent() != null && !"".equals(record.getAgent())) {
			query.addCriteria(Criteria.where("agent").regex(record.getAgent()));
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
		return mongoTemplate.count(query, AgentInvitationRecord.class);
	}

}
