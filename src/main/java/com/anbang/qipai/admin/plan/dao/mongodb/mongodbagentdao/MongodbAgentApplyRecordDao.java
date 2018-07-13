package com.anbang.qipai.admin.plan.dao.mongodb.mongodbagentdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentApplyRecordDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentApplyRecord;
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
	public List<AgentApplyRecord> findAgentApplyRecordByTime(long startTime, long endTime) {
		Query query = new Query();
		Criteria criteria = Criteria.where("createTime").gte(startTime).lte(endTime);
		query.addCriteria(criteria);
		return mongoTempalte.find(query, AgentApplyRecord.class);
	}

}
