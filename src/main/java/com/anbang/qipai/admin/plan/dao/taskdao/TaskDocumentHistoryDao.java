package com.anbang.qipai.admin.plan.dao.taskdao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;

public interface TaskDocumentHistoryDao {

	List<TaskDocumentHistory> findTaskDocumentHistory(int page, int size, Sort sort, TaskDocumentHistory task);

	long getAmount(TaskDocumentHistory task);

	void addTaskDocumentHistory(TaskDocumentHistory task);

	boolean updateTaskState(String taskId, int state);
}
