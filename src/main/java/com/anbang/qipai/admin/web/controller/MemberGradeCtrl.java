package com.anbang.qipai.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Grade.MemberGrade;
import com.anbang.qipai.admin.plan.service.MemberGradeService;
import com.anbang.qipai.admin.remote.service.QipaiMembersService;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**会员等级controller
 * **/
@RestController
@RequestMapping("/grade")
public class MemberGradeCtrl {
	
	@Autowired
	private MemberGradeService memberGradeService;
	
	private static Logger logger = LoggerFactory.getLogger(MemberGradeCtrl.class);
	
	@Autowired
	private QipaiMembersService qipaiMembersService;

	@RequestMapping("/insert_grade")
	@ResponseBody
	public CommonVO insert_grade(MemberGrade memberGradeDbo) {
		CommonVO co = new CommonVO();
		logger.info("vip等级："+memberGradeDbo.getVip1()+memberGradeDbo.getVip2());
		qipaiMembersService.grade_insert_grade(memberGradeDbo);
		return co;
	}
	
	
	@RequestMapping("/find_grade")
	@ResponseBody
	public CommonVO find_grade() {
		CommonVO co = new CommonVO();
		MemberGrade memberGradeDbo = memberGradeService.find_grade();
		co.setData(memberGradeDbo);
		return co;
	}
}
