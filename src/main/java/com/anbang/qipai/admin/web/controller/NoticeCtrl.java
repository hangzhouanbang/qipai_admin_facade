package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.service.NoticeService;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.UserVo;

/**
 * 系统通告controller
 * 
 * @author 程佳 2018.5.31
 **/
@RestController
@RequestMapping("noticectrl")
public class NoticeCtrl {

	@Autowired
	private NoticeService noticeService;
	
	private static Logger logger = LoggerFactory.getLogger(NoticeCtrl.class);

	@Autowired
	private QipaiGameRomoteService qipaiGameRomoteService;

	/**
	 * 查询公告记录
	 * 
	 * @param page
	 *            当前页
	 * @param size
	 *            每页数量
	 **/
	@RequestMapping("/querynotice")
	@ResponseBody
	public Map<String, Object> queryNotice(Integer page, Integer size) {
		Map<String, Object> map = noticeService.findAll(page, size);
		return map;
	}

	/**
	 * 添加系统公告
	 * @param notice 公告内容
	 **/
	@RequestMapping("/addnotice")
	@ResponseBody
	public String addNotice(String notice,String place,HttpSession session) {
		UserVo uservo = (UserVo) session.getAttribute("user");
		Admin admin = uservo.getAdmin();
		logger.info("mail:"+admin.getNickname());
		CommonRemoteVO co = qipaiGameRomoteService.addNotice(notice,place,admin.getNickname());
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
	public String updateNotice() {
		CommonRemoteVO co = qipaiGameRomoteService.updateNotice();
		if (co.isSuccess()) {
			return "success";
		} else {
			return "file";
		}

	}
}
