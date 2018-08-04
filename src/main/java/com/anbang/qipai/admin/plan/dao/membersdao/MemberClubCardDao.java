package com.anbang.qipai.admin.plan.dao.membersdao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.members.MemberClubCard;

public interface MemberClubCardDao {

	List<MemberClubCard> findAllClubCard();

	MemberClubCard getClubCardById(String clubCardId);

	void addClubCard(MemberClubCard clubCard);

	boolean deleteClubCardByIds(String[] clubCardIds);

	boolean updateClubCard(MemberClubCard clubCard);

	MemberClubCard findClubCardByTime(long time);

}
