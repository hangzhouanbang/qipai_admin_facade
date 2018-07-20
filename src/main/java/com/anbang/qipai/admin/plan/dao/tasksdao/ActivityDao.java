package com.anbang.qipai.admin.plan.dao.tasksdao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.tasks.Activity;

public interface ActivityDao {
	void addActivity(Activity activity);

	boolean updateActivityStateById(String activityId, String state);

	List<Activity> findActivityByConditions(int page, int size, Activity activity);

	long getAmountByConditions(Activity activity);
}
