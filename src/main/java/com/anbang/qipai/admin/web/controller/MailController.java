package com.anbang.qipai.admin.web.controller;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.mail.MailReward;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.MailService;
import com.anbang.qipai.admin.remote.MailQipaClients;

import net.sf.json.JSONObject;


/**系统邮件controller
 * @author 程佳 2018.6.6
 * **/
@RestController
@RequestMapping("/mailctrl")
public class MailController {
	
	private static Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private MailQipaClients mailQipaClients;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/addmail")
	@ResponseBody
	public String addmail(SystemMail mail) {
		JSONObject json = JSONObject.fromObject(mail);
		String str = json.toString();
		logger.info(str);
		mailQipaClients.addmail(str);
		return "success";
	}
	
	@RequestMapping("/querymail")
	@ResponseBody
	public Map<String,Object> querymail(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10")Integer size){
		Map<String,Object> map = mailService.findall(page, size);
		return map;
	}

}
