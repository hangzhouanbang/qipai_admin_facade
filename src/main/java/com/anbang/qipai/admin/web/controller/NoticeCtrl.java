package com.anbang.qipai.admin.web.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.plan.service.NoticeService;
import com.anbang.qipai.admin.remote.NoticeQipaClients;


 /**
  * 系统通告controller
  * @author 程佳 2018.5.31
 **/
@Controller
public class NoticeCtrl {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeQipaClients noticeQipaClients;
	
	
	/**查询公告记录
	 * @param page 当前页
	 * @param size 每页数量
	 * **/
	 @RequestMapping("/queryNotice")
	 @ResponseBody
	 public Map<String,Object> queryNotice(Integer page,Integer size){
		 Map<String,Object> map = noticeService.findAll(page, size);
	 	return map;
	 }
	
	
	/**添加系统公告
	 * 	@param notice 公告内容
	 * **/
	@RequestMapping("/addNotice")
	@ResponseBody
	public String addNotice(String notice) {
			noticeQipaClients.addNotice(notice);
			return "success";
		
	}
}
