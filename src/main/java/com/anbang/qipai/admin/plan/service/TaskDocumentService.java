package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.TaskDocumentDao;
import com.anbang.qipai.admin.plan.dao.TaskDocumentHistoryDao;
import com.anbang.qipai.admin.plan.domain.TaskDocument;
import com.anbang.qipai.admin.plan.domain.TaskDocumentHistory;
import com.highto.framework.web.page.ListPage;

@Service
public class TaskDocumentService {

	@Autowired
	private TaskDocumentDao taskDocumentDao;

	@Autowired
	private TaskDocumentHistoryDao taskDocumentHistoryDao;

	public void addTaskDocument(TaskDocument taskDoc) {
		taskDocumentDao.addTaskDocument(taskDoc);
	}

	public void deleteTaskDocuments(String[] taskIds) {
		taskDocumentDao.deleteTaskDocuments(taskIds);
	}

	public void updateTaskDocument(TaskDocument taskDoc) {
		taskDocumentDao.updateTaskDocument(taskDoc);
	}

	public ListPage findTaskDocuments(int page, int size, TaskDocument taskDoc) {
		long amount = taskDocumentDao.getAmount(taskDoc);
		List<TaskDocument> taskDocList = taskDocumentDao.findTaskDocuments(page, size, taskDoc);
		ListPage listPage = new ListPage(taskDocList, page, size, (int) amount);
		return listPage;
	}

	public void release(String taskId, String admin) {
		TaskDocument taskDocument = taskDocumentDao.findDocumentById(taskId);
		TaskDocumentHistory taskDocumentHistory = new TaskDocumentHistory();
		taskDocumentHistory.setName(taskDocument.getName());
		taskDocumentHistory.setDesc(taskDocument.getDesc());
		taskDocumentHistory.setCriterions(taskDocument.getCriterions());
		taskDocumentHistory.setReward(taskDocument.getReward());
		taskDocumentHistory.setTaskType(taskDocument.getTaskType());
		taskDocumentHistory.setState("RELEASE");
		taskDocumentHistory.setPromulgator(admin);
		taskDocumentHistory.setReleaseTime(System.currentTimeMillis());
		taskDocumentHistoryDao.addTaskDocumentHistory(taskDocumentHistory);
	}
}
