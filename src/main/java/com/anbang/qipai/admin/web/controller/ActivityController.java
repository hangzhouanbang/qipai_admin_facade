package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.tasks.Activity;
import com.anbang.qipai.admin.plan.service.tasksservice.ActivityService;
import com.anbang.qipai.admin.remote.service.QipaiTasksRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private QipaiTasksRemoteService qipaiTasksRemoteService;

	@RequestMapping("/addactivity")
	public CommonVO addActivity(Activity activity) {
		CommonVO vo = new CommonVO();
		if (activity.getTheme() == null || activity.getContent() == null || activity.getUrl() == null
				|| activity.getPromulgator() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.activity_add(activity);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			String id = (String) map.get("id");
			String state = (String) map.get("state");
			activity.setId(id);
			activity.setState(state);
			activityService.addActivity(activity);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/startactivity")
	public CommonVO startActivity(@RequestParam(required = true) String activityId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.activity_start(activityId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			String id = (String) map.get("id");
			String state = (String) map.get("state");
			activityService.updateActivityState(id, state);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/stopactivity")
	public CommonVO stopActivity(@RequestParam(required = true) String activityId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.activity_stop(activityId);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			String id = (String) map.get("id");
			String state = (String) map.get("state");
			activityService.updateActivityState(id, state);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/queryactivity")
	public CommonVO queryActivity(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Activity activity) {
		CommonVO vo = new CommonVO();
		ListPage listPage = activityService.findActivityByConditions(page, size, activity);
		vo.setSuccess(true);
		vo.setMsg("activities");
		vo.setData(listPage);
		return vo;
	}
}
