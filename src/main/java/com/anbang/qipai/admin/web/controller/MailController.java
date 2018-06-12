package com.anbang.qipai.admin.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.MailService;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;

import net.sf.json.JSONObject;


/**系统邮件controller
 * @author 程佳 2018.6.6
 * **/
@RestController
@RequestMapping("/mailctrl")
public class MailController {
	
	private static Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private QipaiGameRomoteService qipaiGameRomoteService;
	
	@Autowired
	private MailService mailService;
	
	/**发布系统邮件
	 * @param mail 邮件信息
	 * **/
	@RequestMapping("/addmail")
	@ResponseBody
	public String addmail(SystemMail mail) {
		logger.info("mail:"+mail.getTitle());
		JSONObject json = JSONObject.fromObject(mail);
		String str = json.toString();
		logger.info(str);
		//qipaiGameRomoteService.addmail(str);
		return "success";
	}
	
	/**查询历史维护，恢复记录
	 * @param page 当前页，size 每页显示条数
	 * **/
	@RequestMapping("/querymail")
	@ResponseBody
	public Map<String,Object> querymail(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10")Integer size){
		Map<String,Object> map = mailService.findall(page, size);
		return map;
	}

}
