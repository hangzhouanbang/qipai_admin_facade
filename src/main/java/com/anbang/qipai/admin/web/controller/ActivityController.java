package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.bean.tasks.Activity;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.plan.service.tasksservice.ActivityService;
import com.anbang.qipai.admin.remote.service.QipaiTasksRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 游戏活动
 * 
 * @author 林少聪 2018.8.6
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private QipaiTasksRemoteService qipaiTasksRemoteService;

	@Autowired
	private AdminAuthService adminAuthService;

	@Autowired
	private AdminService adminService;

	/**
	 * 添加活动，同时启用
	 * 
	 * @param activity
	 * @return
	 */
	@RequestMapping("/addactivity")
	public CommonVO addActivity(Activity activity, String token) {
		CommonVO vo = new CommonVO();
		if (activity.getTheme() == null || activity.getContent() == null || activity.getUrl() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId == null) {
			vo.setSuccess(false);
			return vo;
		}
		Admin admin = adminService.findAdminById(adminId);
		activity.setPromulgator(admin.getNickname());
		CommonRemoteVO rvo = qipaiTasksRemoteService.activity_add(activity);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 启用活动
	 * 
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/startactivity")
	public CommonVO startActivity(@RequestParam(required = true) String activityId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiTasksRemoteService.activity_start(activityId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 终止活动
	 * 
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/stopactivity")
	public CommonVO stopActivity(@RequestParam(required = true) String activityId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiTasksRemoteService.activity_stop(activityId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 查询活动
	 * 
	 * @param page
	 * @param size
	 * @param activity
	 * @return
	 */
	@RequestMapping("/queryactivity")
	public CommonVO queryActivity(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, Activity activity) {
		CommonVO vo = new CommonVO();
		ListPage listPage = activityService.findActivityByConditions(page, size, activity);
		vo.setSuccess(true);
		vo.setMsg("activities");
		vo.setData(listPage);
		return vo;
	}
}
