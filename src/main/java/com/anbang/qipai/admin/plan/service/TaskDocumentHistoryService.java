package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.TaskDocumentHistoryDao;
import com.anbang.qipai.admin.plan.domain.TaskDocumentHistory;
import com.highto.framework.web.page.ListPage;

@Service
public class TaskDocumentHistoryService {
	@Autowired
	private TaskDocumentHistoryDao taskDocumentHistoryDao;

	public ListPage findTaskDocumentHistorys(int page, int size, TaskDocumentHistory taskDoc) {
		long amount = taskDocumentHistoryDao.getAmount(taskDoc);
		List<TaskDocumentHistory> taskDocList = taskDocumentHistoryDao.findTaskDocumentHistorys(page, size, taskDoc);
		ListPage listPage = new ListPage(taskDocList, page, size, (int) amount);
		return listPage;
	}

	public void updateState(String taskId, String state) {
		taskDocumentHistoryDao.updateState(taskId, state);
	}
}
