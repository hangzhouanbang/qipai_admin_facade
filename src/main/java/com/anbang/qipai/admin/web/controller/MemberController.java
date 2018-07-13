package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.anbang.qipai.admin.plan.service.MemberGoldService;
import com.anbang.qipai.admin.plan.service.MemberScoreService;
import com.anbang.qipai.admin.plan.service.MemberDboService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 会员controller
 * 
 * @author 林少聪 2018.6.4
 *
 */
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberDboService memberService;

	@Autowired
	private QipaiMembersRemoteService qipaiMembersRemoteService;

	@Autowired
	private MemberGoldService memberGoldService;

	@Autowired
	private MemberScoreService memberScoreService;

	@RequestMapping("/querymember")
	public CommonVO queryMember(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, MemberDbo member) {
		CommonVO vo = new CommonVO();
		ListPage listPage = memberService.findMemberDboByConditions(page, size, member);
		vo.setSuccess(true);
		vo.setMsg("memberList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 批量赠送金币或积分
	 **/
	@RequestMapping("/give_reward")
	public CommonRemoteVO give_reward(@RequestParam(value = "id") String[] ids, Integer score, Integer gold) {
		// kafka发消息
		CommonRemoteVO vo = qipaiMembersRemoteService.give_score_gold(ids, score, gold);
		if (vo.isSuccess()) {
			vo.setSuccess(true);
			return vo;
		}
		vo.setSuccess(false);
		return vo;
	}

	@RequestMapping("/querygoldrecord")
	public CommonVO queryGoldRecord(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, String memberId) {
		CommonVO vo = new CommonVO();
		if (memberId == null) {
			vo.setSuccess(false);
			vo.setMsg("memberId is null");
			return vo;
		}
		ListPage listPage = memberGoldService.findGoldRecordByMemberId(page, size, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/queryscorerecord")
	public CommonVO queryScoreRecord(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String memberId) {
		CommonVO vo = new CommonVO();
		if (memberId == null) {
			vo.setSuccess(false);
			vo.setMsg("memberId is null");
			return vo;
		}
		ListPage listPage = memberScoreService.findScoreRecordByMemberId(page, size, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}
}
