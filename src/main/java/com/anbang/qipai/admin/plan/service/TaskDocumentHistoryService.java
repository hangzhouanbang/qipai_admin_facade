package com.anbang.qipai.admin.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.ClubCardDao;
import com.anbang.qipai.admin.plan.dao.taskdao.TaskDocumentHistoryDao;
import com.anbang.qipai.admin.plan.domain.task.TaskDocument;
import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
import com.highto.framework.web.page.ListPage;

@Service
public class TaskDocumentHistoryService {

	@Autowired
	private TaskDocumentHistoryDao taskDocumentHistoryDao;

	@Autowired
	private ClubCardDao clubCardDao;

	public ListPage queryTaskDocumentHistory(int page, int size, Sort sort, TaskDocumentHistory task) {
		long amount = taskDocumentHistoryDao.getAmount(task);
		List<TaskDocumentHistory> taskList = taskDocumentHistoryDao.findTaskDocumentHistory(page, size, sort, task);
		ListPage listPage = new ListPage(taskList, page, size, (int) amount);
		return listPage;
	}

	public void addTaskDocumentHistory(TaskDocumentHistory task) {
		taskDocumentHistoryDao.addTaskDocumentHistory(task);
	}

	public boolean updateTaskState(String taskId, int state) {
		return taskDocumentHistoryDao.updateTaskState(taskId, state);
	}

	public TaskDocumentHistory releaseTaskDocumentHistory(TaskDocument taskDoc, Map<String, String> params) {
		if (params.get("promulgator") == null) {
			return null;
		}
		TaskDocumentHistory task = new TaskDocumentHistory();
		task.setPromulgator(params.get("promulgator"));
		params.remove("promulgator");
		task.setTaskDocId(taskDoc.getId());
		task.setName(taskDoc.getName());
		task.setDesc(taskDoc.getDesc());
		task.setType(taskDoc.getType());
		task.setTaskName(taskDoc.getTaskName());
		task.setRewardType(taskDoc.getRewardType());
		if (taskDoc.getRewardType().equals("金币奖励")) {
			if (params.get("rewardNum") == null) {
				return null;
			}
			task.setGold(Integer.valueOf(params.get("rewardNum")));
			params.remove("rewardNum");
		}
		if (taskDoc.getRewardType().equals("积分奖励")) {
			if (params.get("rewardNum") == null) {
				return null;
			}
			task.setScore(Integer.valueOf(params.get("rewardNum")));
			params.remove("rewardNum");
		}
		if (taskDoc.getRewardType().equals("会员卡奖励")) {
			if (params.get("clubCardId") == null) {
				return null;
			}
			task.setClubCard(clubCardDao.getClubCardById(params.get("clubCardId")));
			params.remove("clubCardId");
		}
		task.setCriterions(params);
		return task;
	}
}
