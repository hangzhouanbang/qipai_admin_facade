package com.anbang.qipai.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.MemberDbo;
import com.anbang.qipai.admin.plan.service.MemberGoldService;
import com.anbang.qipai.admin.plan.service.MemberScoreService;
import com.anbang.qipai.admin.plan.service.MemberService;
import com.anbang.qipai.admin.remote.service.QipaiMembersService;
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
public class MemberCtrl {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private QipaiMembersService qipaiMembersService;

	@Autowired
	private MemberGoldService memberGoldService;

	@Autowired
	private MemberScoreService memberScoreService;

	@RequestMapping("/querymember")
	public CommonVO queryMember(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, MemberDbo member) {
		CommonVO vo = new CommonVO();
		Sort sort = new Sort(new Order("id"));
		ListPage listPage = memberService.queryByConditionsAndPage(page, size, sort, member);
		vo.setSuccess(true);
		vo.setMsg("memberList");
		vo.setData(listPage);
		return vo;
	}
	
	/**批量赠送金币或积分
	 * **/
	@RequestMapping("/give_reward")
	public CommonRemoteVO give_reward(@RequestParam(value = "id")String[] id,Integer score,Integer gold) {
		//kafka发消息
		System.out.println("数组长度："+id.length);
		System.out.println(score);
		System.out.println(gold);
		CommonRemoteVO vo = qipaiMembersService.update_score_gold(id, score, gold);
		if(vo.isSuccess()) {
			vo.setSuccess(true);
			return vo;
		}
		vo.setSuccess(false);
		return vo;
	}
	
	

	@RequestMapping("/querygoldrecord")
	public CommonVO queryGoldRecord(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, String memberId) {
		CommonVO vo = new CommonVO();
		if (memberId == null) {
			vo.setSuccess(false);
			vo.setMsg("memberId is null");
			return vo;
		}
		Sort sort = new Sort(new Order("id"));
		ListPage listPage = memberGoldService.findGoldRecordById(page, size, sort, memberId);
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
		Sort sort = new Sort(new Order("id"));
		ListPage listPage = memberScoreService.findScoreRecordById(page, size, sort, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}
}
