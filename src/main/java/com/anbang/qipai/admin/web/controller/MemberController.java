package com.anbang.qipai.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginLimitRecord;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberGoldService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginLimitRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberScoreService;
import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;
import com.highto.framework.web.page.ListPage;

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
	private MemberLoginRecordService memberLoginRecordService;

	@Autowired
	private MemberLoginLimitRecordService memberLoginLimitRecordService;

	/**
	 * 查询用户
	 * 
	 * @param page
	 * @param size
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/querymember", method = RequestMethod.POST)
	public CommonVO queryMember(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, MemberVO member) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap();
		ListPage listPage = memberService.findMemberDboByConditions(page, size, member);
		data.put("memberList", listPage);
		long amount = memberService.countAmount();
		data.put("amount", amount);
		long vipAmount = memberService.countVipMember();
		data.put("vipAmount", vipAmount);
		long noVipAmount = amount - vipAmount;
		data.put("noVipAmount", noVipAmount);
		vo.setSuccess(true);
		vo.setMsg("memberList");
		vo.setData(data);
		return vo;
	}

	@RequestMapping(value = "/querymemberdetail", method = RequestMethod.POST)
	public CommonVO queryMemberDetail(String memberId) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap();
		MemberDbo memberDbo = memberService.findMemberById(memberId);
		data.put("realName", memberDbo.getRealName());
		data.put("gender", memberDbo.getGender());
		data.put("phone", memberDbo.getPhone());
		data.put("IDcard", memberDbo.getIDcard());
		data.put("cost", memberDbo.getCost());
		long onlineTime = 0;
		String loginIp = "";
		String loginTime = "";
		MemberLoginRecord loginRecord = memberLoginRecordService.findRecentRecordByMemberId(memberId);
		if (loginRecord != null) {
			onlineTime = loginRecord.getOnlineTime() / 60000;
			loginIp = loginRecord.getLoginIp();
			loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(loginRecord.getLoginTime()));
		}
		data.put("onlineTime", onlineTime + "m");
		data.put("loginIp", loginIp);
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
	@RequestMapping(value = "/give_reward", method = RequestMethod.POST)
	public CommonRemoteVO give_reward(@RequestParam(value = "id") String[] ids, Integer score, Integer gold) {
		CommonRemoteVO vo = qipaiMembersRemoteService.give_score_gold(ids, score, gold);
		vo.setSuccess(true);
		return vo;
	}

	@RequestMapping(value = "/give_reward_clubcard", method = RequestMethod.POST)
	public CommonRemoteVO give_reward_clubcard(@RequestParam(value = "id") String[] ids, Integer vipTime) {
		long day = 24 * 60 * 60 * 1000;
		CommonRemoteVO vo = qipaiMembersRemoteService.give_viptime(ids, vipTime * day);
		vo.setSuccess(vo.isSuccess());
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
	@RequestMapping(value = "/querygoldrecord", method = RequestMethod.POST)
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
	@RequestMapping(value = "/queryscorerecord", method = RequestMethod.POST)
	public CommonVO queryScoreRecord(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, String memberId) {
		CommonVO vo = new CommonVO();
		ListPage listPage = memberScoreService.findScoreRecordByMemberId(page, size, memberId);
		vo.setSuccess(true);
		vo.setMsg("goldrecordList");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/querylimit", method = RequestMethod.POST)
	public CommonVO queryLimit(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, String memberId) {
		CommonVO vo = new CommonVO();
		Map data = new HashMap<>();
		ListPage listPage = memberLoginLimitRecordService.findMemberLoginLimitRecordByMemberId(page, size, memberId);
		data.put("listPage", listPage);
		vo.setSuccess(true);
		vo.setMsg("limit records");
		vo.setData(data);
		return vo;
	}

	@RequestMapping(value = "/addlimit", method = RequestMethod.POST)
	public CommonVO addLimit(MemberLoginLimitRecord record) {
		CommonVO vo = new CommonVO();
		MemberDbo memberDbo = memberService.findMemberById(record.getMemberId());
		if (memberDbo == null) {
			vo.setSuccess(false);
			vo.setMsg("invalid memberId");
			return vo;
		}
		record.setEfficient(true);
		record.setNickname(memberDbo.getNickname());
		record.setCreateTime(System.currentTimeMillis());
		qipaiMembersRemoteService.addlimit(record);
		vo.setSuccess(true);
		return vo;
	}

	@RequestMapping(value = "/deletelimits", method = RequestMethod.POST)
	public CommonVO deleteLimits(@RequestParam(value = "recordId") String[] recordIds) {
		CommonVO vo = new CommonVO();
		qipaiMembersRemoteService.deletelimits(recordIds);
		vo.setSuccess(true);
		return vo;
	}
}
