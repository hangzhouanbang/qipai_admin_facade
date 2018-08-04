package com.anbang.qipai.admin.plan.service.tasksservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.tasks.TaskDocument;
import com.anbang.qipai.admin.plan.dao.tasksdao.TaskDocumentDao;
import com.highto.framework.web.page.ListPage;

@Service
public class TaskDocumentService {

	@Autowired
	private TaskDocumentDao taskDocumentDao;

	public void addTaskDocument(TaskDocument taskDoc) {
		SimpleDateFormat format = new SimpleDateFormat("ssMMdd");
		Date date = new Date();
		String id = format.format(date);
		taskDoc.setId(id);
		taskDocumentDao.addTaskDocument(taskDoc);
	}

	public boolean deleteTaskDocuments(String[] taskDocIds) {
		return taskDocumentDao.deleteTaskDocuments(taskDocIds);
	}

	public boolean updateTaskDocument(TaskDocument taskDoc) {
		return taskDocumentDao.updateTaskDocument(taskDoc);
	}

	public ListPage findTaskDocuments(int page, int size, TaskDocument taskDoc) {
		long amount = taskDocumentDao.getAmount(taskDoc);
		List<TaskDocument> taskDocList = taskDocumentDao.findTaskDocuments(page, size, taskDoc);
		ListPage listPage = new ListPage(taskDocList, page, size, (int) amount);
		return listPage;
	}

	public TaskDocument findTaskDocumentById(String taskDocId) {
		return taskDocumentDao.findTaskDocumentById(taskDocId);
	}
}
