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
	
	public boolean updateAgentClubCard(AgentClubCard card) {
		return clubCardDao.updateAgentClubCard(card);
	}

	public boolean deleteAgentClubCard(String[] cardIds) {
		return clubCardDao.deleteAgentClubCard(cardIds);
	}
}
