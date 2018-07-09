package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.ClubCard;

public interface ClubCardDao {

	List<ClubCard> findAllClubCard();

	ClubCard getClubCardById(String clubCardId);

	void addClubCard(ClubCard clubCard);

	boolean deleteClubCardByIds(String[] clubCardIds);

	boolean updateClubCard(ClubCard clubCard);

	ClubCard findClubCardByTime(long time);

}
