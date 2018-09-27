package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.service.gameservice.NoticeService;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 系统通告controller
 * 
 * @author 程佳 2018.5.31
 **/
@CrossOrigin
@RestController
@RequestMapping("/noticectrl")
public class NoticeCtrl {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private QipaiGameRemoteService qipaiGameRomoteService;

	@Autowired
	private AdminAuthService adminAuthService;

	@Autowired
	private AdminService adminService;

	/**
	 * 查询公告记录
	 * 
	 * @param page 当前页
	 * @param size 每页数量
	 **/
	@RequestMapping("/querynotice")
	@ResponseBody
	public Map<String, Object> queryNotice(Integer page, Integer size) {
		Map<String, Object> map = noticeService.findAll(page, size);
		return map;
	}

	/**
	 * 添加系统公告
	 * 
	 * @param notice 公告内容
	 **/
	@RequestMapping("/addnotice")
	@ResponseBody
	public String addNotice(String notice, String place, String token) {
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId == null) {
			return "fail";
		}
		Admin admin = adminService.findAdminById(adminId);
		CommonRemoteVO co = qipaiGameRomoteService.addNotice(notice, place, admin.getNickname());
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}

	}

	/**
	 * 修改系统公告状态
	 **/
	@RequestMapping("/updatenotice")
	@ResponseBody
	public String updateNotice(String id, String notice, String place, Integer state, String token) {
		String adminId = adminAuthService.getAdminIdBySessionId(token);
		if (adminId == null) {
			return "fail";
		}
		Admin admin = adminService.findAdminById(adminId);
		CommonRemoteVO co = qipaiGameRomoteService.updateNotice(id, notice, place, state, admin.getNickname());
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}

	}
}
