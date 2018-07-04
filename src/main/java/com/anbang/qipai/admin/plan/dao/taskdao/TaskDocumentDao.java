package com.anbang.qipai.admin.plan.dao.taskdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.task.TaskDocument;

public interface TaskDocumentDao {

	long getAmount(TaskDocument taskDoc);

	void addTaskDocument(TaskDocument taskDoc);

	boolean deleteTaskDocuments(String[] taskDocIds);

	boolean updateTaskDocument(TaskDocument taskDoc);

	List<TaskDocument> findTaskDocuments(int page, int size, TaskDocument taskDoc);

	TaskDocument findTaskDocumentById(String taskDocId);

}
