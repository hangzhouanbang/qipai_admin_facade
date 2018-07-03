package com.anbang.qipai.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Admin;
import com.anbang.qipai.admin.plan.domain.ClubCard;
import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.ClubCardService;
import com.anbang.qipai.admin.plan.service.MailService;
import com.anbang.qipai.admin.plan.service.MemberService;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.util.TimeUtil;
import com.anbang.qipai.admin.web.vo.UserVo;
import com.qiniu.util.Auth;

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
	private ClubCardService clubCardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	/**发布系统邮件
	 * @param mail 邮件信息
	 * **/
	@RequestMapping("/addmail")
	@ResponseBody
	public String addmail(SystemMail mail,HttpSession session) {
		UserVo uservo = (UserVo) session.getAttribute("user");
//		Admin admin = uservo.getAdmin();
//		mail.setAdminname(admin.getNickname());
		JSONObject json = JSONObject.fromObject(mail);
		String str = json.toString();
		qipaiGameRomoteService.addmail(str);
		return "success";
	}
	
	/**查询历史维护，恢复记录
	 * @param page 当前页，size 每页显示条数,admin 根据管理员名称查询,status,邮件状态
	 * **/
	@RequestMapping("/querymail")
	@ResponseBody
	public Map<String,Object> querymail(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10")Integer size,String adminname,Integer status){
		Map<String,Object> map = mailService.findall(page, size,adminname,status);
		return map;
	}
	
	/**邮件图片上传，获取七牛云token
	 * **/
	@RequestMapping("/uptoken")
	@ResponseBody
	public CommonRemoteVO uptoken(String accessKey,String secretKey,String bucket) {
		CommonRemoteVO co = new CommonRemoteVO();
//		String accessKey = "qQj7mRKyvE7dOOjObMC8W58i6Yn3penfr7-_fg4d";
//		String secretKey = "9f70kmAddF1maP1U0jy0vRNAhwWNv_huR1xDSH_s";
//		String bucket = "anbang";
		Auth auth = Auth.create(accessKey, secretKey);
		String uptoken = auth.uploadToken(bucket);
		co.setSuccess(true);
		co.setData(uptoken);
		co.setMsg("Obtaintoken");
		return co;
	}
	
	/**查询所有会员卡
	 * **/
	@RequestMapping("/find_vipcard")
	@ResponseBody
	public CommonRemoteVO find_vipcard() {
		CommonRemoteVO co = new CommonRemoteVO();
		List<ClubCard> list = clubCardService.showClubCard();
		co.setSuccess(true);
		co.setData(list);
		return co;
	}
	
	/**管理员根据id发布邮件
	 * **/
	@RequestMapping("/addmailbyid")
	@ResponseBody
	public CommonRemoteVO addMailById(SystemMail mail,@RequestParam(value = "ids")String[] ids,Integer validDay,String vipCardId,HttpSession session) {
		UserVo uservo = (UserVo) session.getAttribute("user");
		CommonRemoteVO vo = new CommonRemoteVO();
//		Admin admin = uservo.getAdmin();
//		mail.setAdminname(admin.getNickname());
		for(String id:ids) {
			MemberDbo memberDbo = memberService.findMemberDbo(id);
			if(memberDbo == null) {
				vo.setMsg("memberId:"+id);
				return vo;
			}
		}
		if(validDay != null) {
			long validTime = TimeUtil.creducedate(System.currentTimeMillis(), validDay);
			mail.setValidTime(validTime);	
		}
		ClubCard clubCard = clubCardService.findClubCardById(vipCardId);
		
		mail.setVipcard(TimeUtil.creduceDay(clubCard.getTime()));
		JSONObject json = JSONObject.fromObject(mail);
		String str = json.toString();
		vo = qipaiGameRomoteService.addMailById(str, ids);
		return vo;
	}
	
	/**查询邮件列表
	 * @param page 当前页，size 每页显示条数,memberId 会员编号,mailType 邮件类型
	 * */
	@RequestMapping("/find_mail_record")
	@ResponseBody
	public CommonRemoteVO find_mail_record(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10")Integer size,String memberId,String mailType,String adminName,long startTime,long endTime) {
		CommonRemoteVO co = new CommonRemoteVO();
		logger.info("开始时间："+startTime+"结束时间："+endTime);
		Map<String,Object> map = mailService.find_mail_record(page, size, memberId, mailType,adminName,startTime,endTime);
		co.setData(map);
		co.setSuccess(true);
		return co;
	}

}
