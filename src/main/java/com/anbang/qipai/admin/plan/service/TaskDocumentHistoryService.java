package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.taskdao.TaskDocumentHistoryDao;
import com.anbang.qipai.admin.plan.domain.task.TaskDocument;
import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
import com.highto.framework.web.page.ListPage;

@Service
public class TaskDocumentHistoryService {

	@Autowired
	private TaskDocumentHistoryDao taskDocumentHistoryDao;

	public ListPage queryTaskDocumentHistory(int page, int size, Sort sort, TaskDocumentHistory task) {
		long amount = taskDocumentHistoryDao.getAmount(task);
		List<TaskDocumentHistory> taskList = taskDocumentHistoryDao.findTaskDocumentHistory(page, size, sort, task);
		ListPage listPage = new ListPage(taskList, page, size, (int) amount);
		return listPage;
	}

	public void addTaskDocumentHistory(TaskDocumentHistory task) {
		taskDocumentHistoryDao.addTaskDocumentHistory(task);
	}

	public boolean updateTaskState(String[] taskIds, int state) {
		return taskDocumentHistoryDao.updateTaskState(taskIds, state);
	}

	public TaskDocumentHistory releaseTaskDocumentHistory(TaskDocument taskDoc, TaskDocumentHistory task) {
		task.setName(taskDoc.getName());
		task.setDesc(taskDoc.getDesc());
		task.setType(taskDoc.getType());
		task.setTaskName(taskDoc.getTaskName());
		return task;
	}
}
