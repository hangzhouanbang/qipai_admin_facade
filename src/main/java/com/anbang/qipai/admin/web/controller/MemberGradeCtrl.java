package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.Grade.MemberGrade;
import com.anbang.qipai.admin.plan.service.MemberGradeService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * 会员等级controller
 **/
@RestController
@RequestMapping("/grade")
public class MemberGradeCtrl {

	@Autowired
	private MemberGradeService memberGradeService;

	@Autowired
	private QipaiMembersRemoteService qipaiMembersService;

	@RequestMapping("/insert_grade")
	@ResponseBody
	public CommonVO insert_grade(MemberGrade memberGradeDbo) {
		CommonVO co = new CommonVO();
		qipaiMembersService.grade_insert_grade(memberGradeDbo);
		return co;
	}

	@RequestMapping("/find_grade")
	@ResponseBody
	public CommonVO find_grade() {
		CommonVO co = new CommonVO();
		MemberGrade memberGrade = memberGradeService.find_grade();
		co.setData(memberGrade);
		return co;
	}
}
