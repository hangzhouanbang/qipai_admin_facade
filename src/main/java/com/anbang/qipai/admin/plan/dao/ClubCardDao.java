package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.ClubCard;

public interface ClubCardDao {

	List<ClubCard> getAllClubCard();

	ClubCard getClubCardById(String clubCardId);

	void addClubCard(ClubCard clubCard);

	Boolean deleteClubCardByIds(String[] clubCardIds);

	Boolean updateClubCard(ClubCard clubCard);
}
