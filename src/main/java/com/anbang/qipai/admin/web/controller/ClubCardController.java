package com.anbang.qipai.admin.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.domain.ClubCard;
import com.anbang.qipai.admin.plan.service.ClubCardService;
import com.anbang.qipai.admin.remote.service.QipaiMembersService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * 会员卡controller
 * 
 * @author 林少聪 2018.7.9
 *
 */
@RestController
@RequestMapping("/clubcard")
public class ClubCardController {

	@Autowired
	private ClubCardService clubCardService;

	@Autowired
	private QipaiMembersService qipaiMembersService;

	@RequestMapping("/showclubcard")
	public CommonVO showClubCards() {
		CommonVO vo = new CommonVO();
		List<ClubCard> cardList = clubCardService.showClubCard();
		vo.setSuccess(true);
		vo.setMsg("clubCardList");
		vo.setData(cardList);
		return vo;
	}

	@RequestMapping("/addclubcard")
	public CommonVO addClubCard(ClubCard clubCard) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiMembersService.clubcard_add(clubCard);
		if (commonRemoteVO.isSuccess()) {
			Map<String, Object> map = (Map<String, Object>) commonRemoteVO.getData();
			ClubCard card = new ClubCard();
			card.setId((String) map.get("id"));
			card.setName((String) map.get("name"));
			card.setPrice((Double) map.get("price"));
			card.setGold((Integer) map.get("gold"));
			card.setScore((Integer) map.get("score"));
			card.setTime((long) ((Integer) map.get("time")));
			clubCardService.addClubCard(card);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deleteclubcard")
	public CommonVO deleteClubCards(@RequestParam(value = "id") String[] clubCardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiMembersService.clubcard_delete(clubCardIds);
		if (commonRemoteVO.isSuccess()) {
			clubCardService.deleteClubCards(clubCardIds);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/updateclubcard")
	public CommonVO updateClubCards(ClubCard clubCard) {
		CommonVO vo = new CommonVO();
		if (clubCard.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("clubCardId is null");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiMembersService.clubcard_update(clubCard);
		if (commonRemoteVO.isSuccess()) {
			clubCardService.updateClubCard(clubCard);
		}
		vo.setSuccess(commonRemoteVO.isSuccess());
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

}
