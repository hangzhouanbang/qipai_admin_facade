package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.tasks.Activity;
import com.anbang.qipai.admin.plan.bean.tasks.TaskDocumentHistory;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-tasks")
public interface QipaiTasksRemoteService {

	@RequestMapping("/task/querytaskconfig")
	public CommonRemoteVO task_querytaskconfig();

	@RequestMapping("/task/querytasktype")
	public CommonRemoteVO task_querytasktype();

	@RequestMapping("/task/release")
	public CommonRemoteVO taskdocument_release(@RequestBody TaskDocumentHistory task);

	@RequestMapping("/task/withdraw")
	public CommonRemoteVO taskdocument_withdraw(@RequestParam(value = "taskId") String taskId);

	@RequestMapping("/activity/addactivity")
	public CommonRemoteVO activity_add(@RequestBody Activity activity);

	@RequestMapping("/activity/startactivity")
	public CommonRemoteVO activity_start(@RequestParam(value = "activityId") String activityId);

	@RequestMapping("/activity/stopactivity")
	public CommonRemoteVO activity_stop(@RequestParam(value = "activityId") String activityId);
}
