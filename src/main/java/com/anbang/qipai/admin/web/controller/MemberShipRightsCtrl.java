package com.anbang.qipai.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.membershiprights.CommonUser;
import com.anbang.qipai.admin.plan.domain.membershiprights.MemberShipRights;
import com.anbang.qipai.admin.plan.domain.membershiprights.VipUser;
import com.anbang.qipai.admin.plan.service.MemberShipRightsService;
import com.anbang.qipai.admin.remote.service.QipaiGameRomoteService;
import com.anbang.qipai.admin.remote.service.QipaiMembersService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 会员权益Controller
 * 
 * @author 程佳 2018.5.31
 **/
@RestController
@RequestMapping("/rights")
public class MemberShipRightsCtrl {

	@Autowired
	private QipaiMembersService qipaMemberClient;
	
	@Autowired
	private MemberShipRightsService memberShipRightsService;
	
	@Autowired
	private QipaiGameRomoteService qipaiGameRomoteService;
	
	private static Logger logger = LoggerFactory.getLogger(MemberShipRightsCtrl.class);

	/**
	 * @param 普通会员所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/commonuser")
	@ResponseBody
	public String commonuser(MemberShipRights commonuser) {
		logger.info("aaa"+commonuser);
		CommonRemoteVO co = qipaMemberClient.commonuser(commonuser.getSignGoldNumber(),commonuser.getGoldForNewNember(),
				commonuser.getShareIntegralNumber(),commonuser.getShareGoldNumber(),commonuser.getInviteIntegralNumber(),
				commonuser.getPlanGrowIntegralSpeed());
		CommonRemoteVO cos = qipaiGameRomoteService.commonuser(commonuser.getPlanMemberRoomsCount(),
				commonuser.getPlanMemberRoomsAliveHours(),commonuser.getPlanMemberMaxCreateRoomDaily(),
				commonuser.getPlanMemberCreateRoomDailyGoldPrice(),commonuser.getPlanMemberCreateRoomDailyGoldPrice());
		if (co.isSuccess() && cos.isSuccess()) {
			return "success";
		} else {
			return "file";
		}
	}
	
	/**
	 * @param vip会员所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/vipuser")
	@ResponseBody
	public String vipuser(MemberShipRights vipUser) {
		CommonRemoteVO co = qipaMemberClient.vipuser(vipUser.getSignGoldNumber(),
				vipUser.getShareIntegralNumber(),
				vipUser.getShareGoldNumber(),vipUser.getInviteIntegralNumber(),
				vipUser.getVipGrowIntegralSpeed(),
				vipUser.getVipGrowGradeSpeed());
		CommonRemoteVO cos = qipaiGameRomoteService.vipuser(vipUser.getVipMemberRoomsCount(),vipUser.getVipMemberRoomsAliveHours());
		if (co.isSuccess() && cos.isSuccess()) {
			return "success";
		} else {
			return "file";
		}
	}
	
	/**查询普通用户所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/commonrights")
	@ResponseBody
	public CommonUser commonrights() {
		CommonUser comm = new CommonUser();
		MemberShipRights memberShipRights = memberShipRightsService.findallCommonUser();
		comm.setSignGoldNumber(memberShipRights.getSignGoldNumber());
		comm.setInviteIntegralNumber(memberShipRights.getInviteIntegralNumber());
		comm.setPlanGrowIntegralSpeed(memberShipRights.getPlanGrowIntegralSpeed());
		comm.setPlanMemberaddRoomDailyGoldPrice(memberShipRights.getPlanMemberaddRoomDailyGoldPrice());
		comm.setPlanMemberCreateRoomDailyGoldPrice(memberShipRights.getPlanMemberCreateRoomDailyGoldPrice());
		comm.setPlanMemberMaxCreateRoomDaily(memberShipRights.getPlanMemberMaxCreateRoomDaily());
		comm.setPlanMemberRoomsAliveHours(memberShipRights.getPlanMemberRoomsAliveHours());
		comm.setPlanMemberRoomsCount(memberShipRights.getPlanMemberRoomsCount());
		comm.setShareGoldNumber(memberShipRights.getShareGoldNumber());
		comm.setShareIntegralNumber(memberShipRights.getShareIntegralNumber());
		comm.setGoldForNewNember(memberShipRights.getGoldForNewNember());
		return comm;
	}
	
	/**查询vip用户所有权益
	 * @return 操作结果
	 **/
	@RequestMapping("/viprights")
	@ResponseBody
	public VipUser viprights() {
		VipUser vip = new VipUser();
		MemberShipRights memberShipRights = memberShipRightsService.findallCommonUser();
		vip.setSignGoldNumber(memberShipRights.getSignGoldNumber());
		vip.setInviteIntegralNumber(memberShipRights.getInviteIntegralNumber());
		vip.setVipGrowIntegralSpeed(memberShipRights.getVipGrowIntegralSpeed());
		vip.setVipMemberRoomsAliveHours(memberShipRights.getVipMemberRoomsAliveHours());
		vip.setVipMemberRoomsCount(memberShipRights.getVipMemberRoomsCount());
		vip.setShareGoldNumber(memberShipRights.getShareGoldNumber());
		vip.setShareIntegralNumber(memberShipRights.getShareIntegralNumber());
		vip.setVipGrowGradeSpeed(memberShipRights.getVipGrowGradeSpeed());
		return vip;
		
	}
	
	
	
	
}
