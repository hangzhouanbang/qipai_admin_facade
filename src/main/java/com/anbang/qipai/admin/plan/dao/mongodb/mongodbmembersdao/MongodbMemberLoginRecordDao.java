package com.anbang.qipai.admin.plan.dao.mongodb.mongodbmembersdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberLoginRecordDao;

@Component
public class MongodbMemberLoginRecordDao implements MemberLoginRecordDao {

	@Autowired
	private MongoTemplate mongTemplate;

	@Override
	public void save(MemberLoginRecord record) {
		mongTemplate.insert(record);
	}

	@Override
	public void updateOnlineTimeById(String id, long onlineTime) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("onlineTime", onlineTime);
		mongTemplate.updateFirst(query, update, MemberLoginRecord.class);
	}

}
