package com.anbang.qipai.admin.plan.service.agentsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.agents.AgentClubCard;
import com.anbang.qipai.admin.plan.dao.agentsdao.AgentClubCardDao;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentClubCardService {

	@Autowired
	private AgentClubCardDao clubCardDao;

	public ListPage findAgentClubCardByConditions(int page, int size, AgentClubCard card) {
		long amount = clubCardDao.getAmountByConditions(card);
		List<AgentClubCard> cardList = clubCardDao.findAgentClubCardByConditions(page, size, card);
		ListPage listPage = new ListPage(cardList, page, size, (int) amount);
		return listPage;
	}

	public void addAgentClubCard(AgentClubCard card) {
		clubCardDao.addAgentClubCard(card);
	}

	public void updateAgentClubCard(AgentClubCard card) {
		clubCardDao.updateAgentClubCard(card);
	}

	public void deleteAgentClubCard(String cardId) {
		clubCardDao.deleteAgentClubCard(cardId);
	}

	public AgentClubCard findAgentClubCardById(String id) {
		return clubCardDao.findAgentClubCardById(id);
	}

	public AgentClubCard updateAgentClubCardRemain(String cardId, int remain) {
		clubCardDao.updateAgentClubCardRemain(cardId, remain);
		return clubCardDao.findAgentClubCardById(cardId);
	}
}
