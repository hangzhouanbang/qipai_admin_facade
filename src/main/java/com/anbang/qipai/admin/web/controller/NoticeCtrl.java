package com.anbang.qipai.admin.web.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.qipai.admin.plan.domain.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;
import com.anbang.qipai.admin.qipaClient.NoticeQipaClients;


 /**
  * 系统通告controller
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
		Integer state = 1;
		Notice notice1 = noticeService.queryByState(state);
		if(notice != null && !notice.equals("")) {
			if(notice1 != null) {//有启用状态的公告,修改启用公告的状态
				Notice notice2 = new Notice();
				notice2.setId(notice1.getId());
				notice2.setNotice(notice1.getNotice());
				notice2.setState(0);
				noticeService.updateNotice(notice2);
			}
			Notice notice3 = new Notice();
			notice3.setState(1);
			notice3.setNotice(notice);
			Notice notice4 = noticeService.addNotice(notice3);
			if(notice4 != null) {//数据库添加成功  直接调用服务
				noticeQipaClients.addNotice(notice);
				return "success";
			}
			return "file";
		}
		return "file";
		
	}
}
