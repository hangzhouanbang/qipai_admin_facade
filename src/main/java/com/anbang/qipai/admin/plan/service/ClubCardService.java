package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.ClubCardDao;
import com.anbang.qipai.admin.plan.domain.ClubCard;

@Service
public class ClubCardService {

	@Autowired
	private ClubCardDao clubCardDao;

	public List<ClubCard> showClubCard() {
		return clubCardDao.getAllClubCard();
	}

	public void addClubCard(ClubCard clubCard) {
		clubCardDao.addClubCard(clubCard);
	}

	public Boolean deleteClubCards(String[] clubCardIds) {
		return clubCardDao.deleteClubCardByIds(clubCardIds);
	}

	public Boolean updateClubCard(ClubCard clubCard) {
		return clubCardDao.updateClubCard(clubCard);
	}
	
	public ClubCard findClubCardById(String id) {
		return clubCardDao.getClubCardById(id);
	}

}
