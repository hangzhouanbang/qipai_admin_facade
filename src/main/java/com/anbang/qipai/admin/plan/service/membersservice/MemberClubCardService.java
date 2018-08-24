package com.anbang.qipai.admin.plan.service.membersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.members.MemberClubCard;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberClubCardDao;

@Service
public class MemberClubCardService {

	@Autowired
	private MemberClubCardDao clubCardDao;

	public List<MemberClubCard> showClubCard() {
		return clubCardDao.findAllClubCard();
	}

	public void addClubCard(MemberClubCard clubCard) {
		clubCardDao.addClubCard(clubCard);
	}

	public void deleteClubCardByIds(String[] clubCardIds) {
		clubCardDao.deleteClubCardByIds(clubCardIds);
	}

	public void updateClubCard(MemberClubCard clubCard) {
		clubCardDao.updateClubCard(clubCard);
	}

	public MemberClubCard findClubCardById(String id) {
		return clubCardDao.getClubCardById(id);
	}

}
