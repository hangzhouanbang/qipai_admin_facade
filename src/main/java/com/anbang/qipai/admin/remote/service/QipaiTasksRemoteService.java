package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-tasks")
public interface QipaiTasksRemoteService {

	@RequestMapping("/task/querytaskconfig")
	public CommonRemoteVO task_querytaskconfig();

	@RequestMapping("/task/querytaskcriterions")
	public CommonRemoteVO task_querytaskcriterions(@RequestParam(value = "taskName") String taskName);

	@RequestMapping("/task/release")
	public CommonRemoteVO taskdocument_release(@RequestBody TaskDocumentHistory task);

	@RequestMapping("/task/withdraw")
	public CommonRemoteVO taskdocument_withdraw(@RequestParam(value = "taskId") String taskId);
}
