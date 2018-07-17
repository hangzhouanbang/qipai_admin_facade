package com.anbang.qipai.admin.plan.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.taskdao.ActivityDao;
import com.anbang.qipai.admin.plan.domain.task.Activity;
import com.highto.framework.web.page.ListPage;

@Service
public class ActivityService {

	@Autowired
	private ActivityDao activityDao;

	public void addActivity(Activity activity) {
		activityDao.addActivity(activity);
	}

	public boolean updateActivityState(String activityId, String state) {
		return activityDao.updateActivityStateById(activityId, state);
	}

	public ListPage findActivityByConditions(int page, int size, Activity activity) {
		long amount = activityDao.getAmountByConditions(activity);
		List<Activity> activities = activityDao.findActivityByConditions(page, size, activity);
		ListPage listPage = new ListPage(activities, page, size, (int) amount);
		return listPage;
	}
}
