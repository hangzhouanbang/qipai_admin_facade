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
		if (clubCard.getName() == null || clubCard.getPrice() == null || clubCard.getGold() == null
				|| clubCard.getScore() == null || clubCard.getTime() == null) {
			vo.setSuccess(false);
			vo.setMsg("admin more than one param is null");
			return vo;
		}
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
			vo.setSuccess(true);
			vo.setMsg("admin add success");
			vo.setData(commonRemoteVO.getData());
			return vo;
		}
		vo.setSuccess(false);
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/deleteclubcard")
	public CommonVO deleteClubCards(@RequestParam(value = "id") String[] clubCardIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO commonRemoteVO = qipaiMembersService.clubcard_delete(clubCardIds);
		if (commonRemoteVO.isSuccess()) {
			if (clubCardService.deleteClubCards(clubCardIds)) {
				vo.setSuccess(true);
				vo.setMsg("admin delete success");
				vo.setData(commonRemoteVO.getData());
				return vo;
			} else {
				vo.setSuccess(false);
				vo.setMsg("admin delete fail");
				vo.setData(commonRemoteVO.getData());
				return vo;
			}
		}
		vo.setSuccess(false);
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

	@RequestMapping("/updateclubcard")
	public CommonVO updateClubCards(ClubCard clubCard) {
		CommonVO vo = new CommonVO();
		if (clubCard.getId() == null) {
			vo.setSuccess(false);
			vo.setMsg("admin clubCardId is null");
			return vo;
		}
		CommonRemoteVO commonRemoteVO = qipaiMembersService.clubcard_update(clubCard);
		if (commonRemoteVO.isSuccess()) {
			if (clubCardService.updateClubCard(clubCard)) {
				vo.setSuccess(true);
				vo.setMsg("admin update success");
				vo.setData(commonRemoteVO.getData());
				return vo;
			} else {
				vo.setSuccess(false);
				vo.setMsg("admin update fail");
				vo.setData(commonRemoteVO.getData());
				return vo;
			}
		}
		vo.setSuccess(false);
		vo.setMsg(commonRemoteVO.getMsg());
		return vo;
	}

}
