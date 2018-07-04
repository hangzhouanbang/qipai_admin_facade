package com.anbang.qipai.admin.plan.dao.mongodb.mongotaskdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.taskdao.TaskDocumentHistoryDao;
import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
import com.mongodb.WriteResult;

@Component
public class MongodbTaskDocumentHistoryDao implements TaskDocumentHistoryDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<TaskDocumentHistory> findTaskDocumentHistory(int page, int size, Sort sort, TaskDocumentHistory task) {
		Query query = new Query();
		if (task.getName() != null) {
			query.addCriteria(Criteria.where("name").regex(task.getName()));
		}
		if (task.getTaskDocId() != null) {
			query.addCriteria(Criteria.where("taskDocId").is(task.getTaskDocId()));
		}
		if (task.getType() != null) {
			query.addCriteria(Criteria.where("type").is(task.getType()));
		}
		query.skip((page - 1) * size);
		query.limit(size);
		query.with(sort);
		return mongoTemplate.find(query, TaskDocumentHistory.class);
	}

	@Override
	public long getAmount(TaskDocumentHistory task) {
		Query query = new Query();
		if (task.getName() != null) {
			query.addCriteria(Criteria.where("name").regex(task.getName()));
		}
		if (task.getTaskDocId() != null) {
			query.addCriteria(Criteria.where("taskDocId").is(task.getTaskDocId()));
		}
		if (task.getType() != null) {
			query.addCriteria(Criteria.where("type").is(task.getType()));
		}
		return mongoTemplate.count(query, TaskDocumentHistory.class);
	}

	@Override
	public void addTaskDocumentHistory(TaskDocumentHistory task) {
		mongoTemplate.insert(task);
	}

	@Override
	public boolean updateTaskState(String taskId, int state) {
		Query query = new Query(Criteria.where("id").is(taskId));
		Update update = new Update();
		update.set("state", state);
		WriteResult result = mongoTemplate.updateFirst(query, update, TaskDocumentHistory.class);
		return result.getN() > 0;
	}

}
