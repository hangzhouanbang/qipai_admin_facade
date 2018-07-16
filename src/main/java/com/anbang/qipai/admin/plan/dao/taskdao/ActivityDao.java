package com.anbang.qipai.admin.plan.dao.taskdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.task.Activity;

public interface ActivityDao {
	void addActivity(Activity activity);

	boolean updateActivityStateById(String activityId, int state);

	List<Activity> findActivityByConditions(int page, int size, Activity activity);

	long getAmountByConditions(Activity activity);
}
