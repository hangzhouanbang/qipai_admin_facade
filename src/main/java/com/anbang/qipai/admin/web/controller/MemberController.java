package com.anbang.qipai.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberGoldService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOrderService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberScoreService;
import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;
import com.mongodb.BasicDBObject;

/**
 * 会员controller
 * 
 * @author 林少聪 2018.6.4
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberDboService memberService;

	@Autowired
	private QipaiMembersRemoteService qipaiMembersRemoteService;

	@Autowired
	private QipaiGameRemoteService qipaiGameRemoteService;

	@Autowired
	private MemberGoldService memberGoldService;

	@Autowired
	private MemberScoreService memberScoreService;

	@Autowired
	private MemberOrderService memberOrderService;

	@Autowired
	private MemberLoginRecordService memberLoginRecordService;

	/**
	 * 查询用户
	 * 
	 * @param page
	 * @param size
	 * @param member
	 * @return
	 */
	@RequestMapping("/querymember")
	public CommonVO queryMember(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, MemberDbo member,
			@RequestParam(value = "queryType", defaultValue = "2") int queryType) {
		CommonVO vo = new CommonVO();
		ListPage listPage = memberService.findMemberDboByConditions(page, size, member,queryType);
		vo.setSuccess(true);
		vo.setMsg("memberList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping("/querymemberdetail")
	public CommonVO queryMemberDetail(String memberId) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap();
		MemberDbo memberDbo = memberService.findMemberById(memberId);
		data.put("memberDbo", memberDbo);
		double cost = memberOrderService.countCostByMemberId(memberId);
		data.put("cost", cost);
		MemberLoginRecord loginRecord = memberLoginRecordService.findRecentRecordByMemberId(memberId);
		long onlineTime = loginRecord.getOnlineTime() / 60000;
		data.put("onlineTime", onlineTime + "m");
		data.put("loginIp", loginRecord.getLoginIp());
		String loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(loginRecord.getLoginTime()));
		data.put("loginTime", loginTime);
		CommonRemoteVO rvo = qipaiGameRemoteService.game_queryMemberPlayingRoom(memberId);
		data.put("roomList", rvo.getData());
		vo.setSuccess(true);
		vo.setMsg("member detail");
		vo.setData(data);
		return vo;
	}

	/**
	 * 批量赠送金币或积分
	 **/
	@RequestMapping("/give_reward")
	public CommonRemoteVO give_reward(@RequestParam(value = "id") String[] ids, Integer score, Integer gold) {
		CommonRemoteVO vo = qipaiMembersRemoteService.give_score_gold(ids, score, gold);
		vo.setSuccess(true);
		return vo;
	}

	/**
	 * 查询会员金币流水
	 * 
	 * @param page
	 * @param size
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/querygoldrecord")
	public CommonVO queryGoldRecord(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, String memberId) {
		CommonVO vo = new CommonVO();
		ListPage listPage = memberGoldService.findGoldRecordByMemberId(page, size, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}

	/**
	 * 查询会员积分流水
	 * 
	 * @param page
	 * @param size
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/queryscorerecord")
	public CommonVO queryScoreRecord(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, String memberId) {
		CommonVO vo = new CommonVO();
		ListPage listPage = memberScoreService.findScoreRecordByMemberId(page, size, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}
}
