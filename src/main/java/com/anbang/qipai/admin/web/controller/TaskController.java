package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.tasks.TaskDocument;
import com.anbang.qipai.admin.plan.bean.tasks.TaskDocumentHistory;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.plan.service.tasksservice.TaskDocumentHistoryService;
import com.anbang.qipai.admin.plan.service.tasksservice.TaskDocumentService;
import com.anbang.qipai.admin.remote.service.QipaiTasksRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 任务管理
 * 
 * @author 林少聪 2018.8.6
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskDocumentService taskDocumentService;

	@Autowired
	private TaskDocumentHistoryService taskDocumentHistoryService;

	@Autowired
	private QipaiTasksRemoteService qipaiTasksRemoteService;

	@Autowired
	private AdminAuthService adminAuthService;

	@Autowired
	private AdminService adminService;

	/**
	 * 查询任务种类、类型
	 * 
	 * @return
	 */
	@RequestMapping("/querytaskconfig")
	public CommonVO queryTaskConfig() {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytaskconfig();
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(commonRemoteVO.getData());
		return vo;
	}

	/**
	 * 查询任务类型
	 * 
	 * @return
	 */
	@RequestMapping("/querytasktype")
	public CommonVO queryTaskType() {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytasktype();
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		vo.setData(commonRemoteVO.getData());
		return vo;
	}

	/**
	 * 查询任务档案
	 * 
	 * @param page
	 * @param size
	 * @param taskDoc
	 * @return
	 */
	@RequestMapping("/querytaskdocument")
	public CommonVO queryTaskDocument(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		ListPage listPage = taskDocumentService.findTaskDocuments(page, size, taskDoc);
		vo.setSuccess(true);
		vo.setMsg("TaskDocumentList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查询任务发布历史记录
	 * 
	 * @param page
	 * @param size
	 * @param task
	 * @return
	 */
	@RequestMapping("/querytaskdocumenthistory")
	public CommonVO queryTaskDocumentHistory(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, TaskDocumentHistory task) {
		CommonVO vo = new CommonVO();
		Sort sort = new Sort(new Order(Direction.ASC, "releaseTime"));
		ListPage listPage = taskDocumentHistoryService.queryTaskDocumentHistory(page, size, sort, task);
		vo.setSuccess(true);
		vo.setMsg("TaskList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 添加任务档案
	 * 
	 * @param taskDoc
	 * @return
	 */
	@RequestMapping("/addtaskdocument")
	public CommonVO addTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		if (taskDoc.getName() == null || taskDoc.getDesc() == null || taskDoc.getTaskName() == null
				|| taskDoc.getType() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		taskDocumentService.addTaskDocument(taskDoc);
		vo.setSuccess(true);
		vo.setMsg("Add TaskDocument");
		return vo;
	}

	/**
	 * 删除任务档案
	 * 
	 * @param taskDocIds
	 * @return
	 */
	@RequestMapping("/deletetaskdocuments")
	public CommonVO deleteTaskDocuments(@RequestParam(value = "taskDocId") String[] taskDocIds) {
		CommonVO vo = new CommonVO();
		taskDocumentService.deleteTaskDocuments(taskDocIds);
		vo.setSuccess(true);
		vo.setMsg("delete taskdocuments");
		return vo;
	}

	/**
	 * 修改任务档案
	 * 
	 * @param taskDoc
	 * @return
	 */
	@RequestMapping("/updatetaskdocument")
	public CommonVO updateTaskDocument(TaskDocument taskDoc) {
		CommonVO vo = new CommonVO();
		taskDocumentService.updateTaskDocument(taskDoc);
		vo.setSuccess(true);
		vo.setMsg("Update TaskDocument");
		return vo;
	}

	/**
	 * 发布任务
	 * 
	 * @param task
	 * @return
	 */
	@RequestMapping("/release")
	public CommonVO releaseTask(TaskDocumentHistory task, String token) {
		CommonVO vo = new CommonVO();
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId == null) {
			vo.setSuccess(false);
			return vo;
		}
		Admin admin = adminService.findAdminById(adminId);
		task.setPromulgator(admin.getNickname());
		TaskDocument taskDoc = taskDocumentService.findTaskDocumentById(task.getTaskDocId());
		if (taskDoc == null) {
			vo.setSuccess(false);
			vo.setMsg("Not Found TaskDocument");
			return vo;
		}
		TaskDocumentHistory taskHistory = taskDocumentHistoryService.releaseTaskDocumentHistory(taskDoc, task);
		CommonRemoteVO rvo = qipaiTasksRemoteService.taskdocument_release(taskHistory);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 撤回任务
	 * 
	 * @param taskIds
	 * @return
	 */
	@RequestMapping("/withdraw")
	public CommonVO withdrawTask(String taskId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiTasksRemoteService.taskdocument_withdraw(taskId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}
}
