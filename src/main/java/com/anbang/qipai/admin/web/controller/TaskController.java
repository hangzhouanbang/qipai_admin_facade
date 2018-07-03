package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.TaskDocument;
import com.anbang.qipai.admin.plan.domain.TaskDocumentHistory;
import com.anbang.qipai.admin.plan.service.TaskDocumentHistoryService;
import com.anbang.qipai.admin.plan.service.TaskDocumentService;
import com.anbang.qipai.admin.remote.service.QipaiTasksRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskDocumentService taskDocumentService;

	@Autowired
	private TaskDocumentHistoryService taskDocumentHistoryService;

	@Autowired
	private QipaiTasksRemoteService qipaiTasksRemoteService;

	@RequestMapping("/querytaskdocument")
	public CommonVO queryTaskDocument(Integer page, Integer size, TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		ListPage listPage = taskDocumentService.findTaskDocuments(page, size, taskDoc);
		vo.setSuccess(true);
		vo.setMsg("taskDocumentList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/addtaskdocument")
	public CommonVO addTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO remoteVO = qipaiTasksRemoteService.taskdocument_add(taskDoc);
		if (remoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) remoteVO.getData();
			TaskDocument task = new TaskDocument();
			task.setId((String) map.get("id"));
			task.setName((String) map.get("name"));
			task.setDesc((String) map.get("desc"));
			task.setTaskType((String) map.get("taskType"));
			task.setCriterions((Map<String, String>) map.get("criterions"));
			task.setReward((Map<String, String>) map.get("reward"));
			taskDocumentService.addTaskDocument(task);
		}
		vo.setSuccess(remoteVO.isSuccess());
		vo.setMsg(remoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deletetaskdocuments")
	public CommonVO deleteTaskDocuments(String[] taskIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO remoteVO = qipaiTasksRemoteService.taskdocument_delete(taskIds);
		if (remoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) remoteVO.getData();
			String[] ids = (String[]) map.get("taskId");
			taskDocumentService.deleteTaskDocuments(ids);
		}
		vo.setSuccess(remoteVO.isSuccess());
		vo.setMsg(remoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/updatetaskdocument")
	public CommonVO updateTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO remoteVO = qipaiTasksRemoteService.taskdocument_update(taskDoc);
		if (remoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) remoteVO.getData();
			TaskDocument task = new TaskDocument();
			task.setId((String) map.get("id"));
			task.setName((String) map.get("name"));
			task.setDesc((String) map.get("desc"));
			task.setTaskType((String) map.get("taskType"));
			task.setCriterions((Map<String, String>) map.get("criterions"));
			task.setReward((Map<String, String>) map.get("reward"));
			taskDocumentService.updateTaskDocument(task);
		}
		vo.setSuccess(remoteVO.isSuccess());
		vo.setMsg(remoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/querytaskdocumenthistory")
	public CommonVO queryTaskDocumentHistory(Integer page, Integer size, TaskDocumentHistory taskDoc) {
		CommonVO vo = new CommonVO();
		ListPage listPage = taskDocumentHistoryService.findTaskDocumentHistorys(page, size, taskDoc);
		vo.setSuccess(true);
		vo.setMsg("taskDocumentHistoryList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/release")
	public CommonVO release(String taskId, String admin) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO remoteVO = qipaiTasksRemoteService.taskdocument_release(taskId, admin);
		vo.setSuccess(remoteVO.isSuccess());
		vo.setMsg(remoteVO.getMsg());
		return vo;
	}
}
