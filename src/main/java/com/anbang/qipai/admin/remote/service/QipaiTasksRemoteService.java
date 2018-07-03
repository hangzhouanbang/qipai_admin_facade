package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.TaskDocument;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-tasks")
public interface QipaiTasksRemoteService {

	@RequestMapping("/task/addtaskdocument")
	public CommonRemoteVO taskdocument_add(@RequestBody TaskDocument taskDoc);

	@RequestMapping("/task/deletetaskdocuments")
	public CommonRemoteVO taskdocument_delete(@RequestBody String[] taskIds);

	@RequestMapping("/task/updatetaskdocument")
	public CommonRemoteVO taskdocument_update(@RequestBody TaskDocument taskDoc);

	@RequestMapping("/task/release")
	public CommonRemoteVO taskdocument_release(@RequestParam(value = "taskId") String taskId,
			@RequestParam(value = "admin") String admin);
}
