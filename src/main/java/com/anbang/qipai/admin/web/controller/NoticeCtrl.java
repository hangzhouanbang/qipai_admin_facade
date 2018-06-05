package com.anbang.qipai.admin.web.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.service.NoticeService;
import com.anbang.qipai.admin.remote.CommonVO;
import com.anbang.qipai.admin.remote.NoticeQipaClients;


 /**
  * 系统通告controller
  * @author 程佳 2018.5.31
 **/
@RestController
@RequestMapping("noticectrl")
public class NoticeCtrl {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeQipaClients noticeQipaClients;
	
	
	/**查询公告记录
	 * @param page 当前页
	 * @param size 每页数量
	 * **/
	 @RequestMapping("/querynotice")
	 @ResponseBody
	 public Map<String,Object> queryNotice(Integer page,Integer size){
		 Map<String,Object> map = noticeService.findAll(page, size);
	 	return map;
	 }
	
	
	/**添加系统公告
	 * 	@param notice 公告内容
	 * **/
	@RequestMapping("/addnotice")
	@ResponseBody
	public String addNotice(String notice) {
			CommonVO co = noticeQipaClients.addNotice(notice);
			if(co.isSuccess()) {
				return "success";	
			}else {
				return "file";
			}
			
		
	}
}
