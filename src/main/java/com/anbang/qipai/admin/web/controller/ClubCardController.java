package com.anbang.qipai.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.members.MemberClubCard;
import com.anbang.qipai.admin.plan.service.membersservice.MemberClubCardService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * 会员卡controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/clubcard")
public class ClubCardController {

	@Autowired
	private MemberClubCardService clubCardService;

	@Autowired
	private QipaiMembersRemoteService qipaiMembersService;

	/**
	 * 查询游戏商城会员卡列表
	 * 
	 * @return
	 */
	@RequestMapping("/showclubcard")
	public CommonVO showClubCard() {
		CommonVO vo = new CommonVO();
		List<MemberClubCard> cardList = clubCardService.showClubCard();
		vo.setSuccess(true);
		vo.setMsg("clubCardList");
		vo.setData(cardList);
		return vo;
	}

	/**
	 * 添加游戏商城会员卡
	 * 
	 * @param clubCard
	 * @return
	 */
	@RequestMapping("/addclubcard")
	public CommonVO addClubCard(MemberClubCard clubCard) {
		CommonVO vo = new CommonVO();
		if (clubCard.getName() == null || clubCard.getGold() == null || clubCard.getScore() == null
				|| clubCard.getPrice() == null || clubCard.getTime() == null) {
			vo.setSuccess(false);
			vo.setMsg("at least one param is null");
			return vo;
		}
		CommonRemoteVO rvo = qipaiMembersService.clubcard_add(clubCard);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	/**
	 * 删除游戏商城会员卡
	 * 
	 * @param clubCardIds
	 * @return
	 */
	@RequestMapping("/deleteclubcard")
	public CommonVO deleteClubCard(@RequestParam(value = "id") String[] clubCardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiMembersService.clubcard_delete(clubCardIds);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

	/**
	 * 修改游戏商城会员卡
	 * 
	 * @param clubCard
	 * @return
	 */
	@RequestMapping("/updateclubcard")
	public CommonVO updateClubCard(MemberClubCard clubCard) {
		CommonVO vo = new CommonVO();
		if (clubCard.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("clubCardId is null");
			return vo;
		}
		CommonRemoteVO rvo = qipaiMembersService.clubcard_update(clubCard);
		if (rvo != null) {
			vo.setSuccess(rvo.isSuccess());
		} else {
			vo.setSuccess(false);
		}
		return vo;
	}

}
