package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentScoreClubCardDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentScoreClubCard;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentScoreClubCardService {

	@Autowired
	private AgentScoreClubCardDao scoreClubCardDao;

	public ListPage findScoreClubCard(int page, int size) {
		long amount = scoreClubCardDao.getAmount();
		List<AgentScoreClubCard> cardList = scoreClubCardDao.findScoreClubCard(page, size);
		ListPage listPage = new ListPage(cardList, page, size, (int) amount);
		return listPage;
	}

	public void addScoreClubCard(AgentScoreClubCard card) {
		scoreClubCardDao.addScoreClubCard(card);
	}

	public boolean deleteScoreClubCard(String[] cardIds) {
		return scoreClubCardDao.deleteScoreClubCard(cardIds);
	}
}
