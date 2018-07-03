package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.TaskDocumentHistory;

public interface TaskDocumentHistoryDao {
	long getAmount(TaskDocumentHistory taskDoc);

	List<TaskDocumentHistory> findTaskDocumentHistorys(int page, int size, TaskDocumentHistory taskDoc);

	void addTaskDocumentHistory(TaskDocumentHistory taskDocumentHistory);

	void updateState(String taskId, String state);
}
