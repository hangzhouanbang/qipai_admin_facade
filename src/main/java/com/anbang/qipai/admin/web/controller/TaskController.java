package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.task.TaskDocument;
import com.anbang.qipai.admin.plan.domain.task.TaskDocumentHistory;
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

	@RequestMapping("/querytaskconfig")
	public CommonVO queryTaskConfig() {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytaskconfig();
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(commonRemoteVO.getData());
		return vo;
	}

	@RequestMapping("/querytaskcriterions")
	public CommonVO queryTaskCriterions(String taskDocId) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(false);
		vo.setMsg("Not Found TaskDocument");
		TaskDocument taskDoc = taskDocumentService.findTaskDocumentById(taskDocId);
		if (taskDoc != null) {
			CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytaskcriterions(taskDoc.getTaskName());
			vo.setSuccess(commonRemoteVO.isSuccess());
			vo.setMsg(commonRemoteVO.getMsg());
			vo.setData(commonRemoteVO.getData());
		}
		return vo;
	}

	@RequestMapping("/querytaskdocument")
	public CommonVO queryTaskDocument(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		ListPage listPage = taskDocumentService.findTaskDocuments(page, size, taskDoc);
		vo.setSuccess(true);
		vo.setMsg("TaskDocumentList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/querytaskdocumenthistory")
	public CommonVO queryTaskDocumentHistory(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, TaskDocumentHistory task) {
		CommonVO vo = new CommonVO();
		Sort sort = new Sort(new Order("releaseTime"));
		ListPage listPage = taskDocumentHistoryService.queryTaskDocumentHistory(page, size, sort, task);
		vo.setSuccess(true);
		vo.setMsg("TaskList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/addtaskdocument")
	public CommonVO addTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		if (taskDoc.getName() == null || taskDoc.getDesc() == null || taskDoc.getTaskName() == null
				|| taskDoc.getType() == null || taskDoc.getRewardType() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		taskDocumentService.addTaskDocument(taskDoc);
		vo.setSuccess(true);
		vo.setMsg("Add TaskDocument");
		return vo;
	}

	@RequestMapping("/deletetaskdocuments")
	public CommonVO deleteTaskDocuments(@RequestParam(value = "taskDocId") String[] taskDocIds) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(false);
		vo.setMsg("Delete Fail");
		if (taskDocumentService.deleteTaskDocuments(taskDocIds)) {
			vo.setSuccess(true);
			vo.setMsg("Delete TaskDocuments");
		}
		return vo;
	}

	@RequestMapping("/updatetaskdocument")
	public CommonVO updateTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(false);
		vo.setMsg("Update Fail");
		if (taskDoc.getId() == null) {
			return vo;
		}
		if (taskDocumentService.updateTaskDocument(taskDoc)) {
			vo.setSuccess(true);
			vo.setMsg("Update TaskDocument");
		}
		return vo;
	}

	@RequestMapping("/release")
	public CommonVO releaseTask(@RequestParam Map<String, String> params) {
		CommonVO vo = new CommonVO();
		TaskDocument taskDoc = taskDocumentService.findTaskDocumentById(params.get("taskDocId"));
		params.remove("taskDocId");
		if (taskDoc == null) {
			vo.setSuccess(false);
			vo.setMsg("Not Found TaskDocument");
			return vo;
		}
		TaskDocumentHistory task = taskDocumentHistoryService.releaseTaskDocumentHistory(taskDoc, params);
		if (task == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.taskdocument_release(task);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			task.setId((String) map.get("id"));
			task.setReleaseTime((long) map.get("releaseTime"));
			task.setState((int) map.get("state"));
			taskDocumentHistoryService.addTaskDocumentHistory(task);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/withdraw")
	public CommonVO withdrawTask(@RequestParam(required = true) String taskId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.taskdocument_withdraw(taskId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			taskDocumentHistoryService.updateTaskState((String) map.get("id"), (int) map.get("state"));
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}
}
