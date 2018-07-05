package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
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
	public CommonRemoteVO taskdocument_withdraw(@RequestBody String[] taskIds);
}
