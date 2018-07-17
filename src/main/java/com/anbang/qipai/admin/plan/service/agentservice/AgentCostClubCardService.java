package com.anbang.qipai.admin.plan.service.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.agentdao.AgentCostClubCardDao;
import com.anbang.qipai.admin.plan.domain.agent.AgentCostClubCard;
import com.highto.framework.web.page.ListPage;

@Service
public class AgentCostClubCardService {
	@Autowired
	private AgentCostClubCardDao costClubCardDao;

	public ListPage findCostClubCard(int page, int size) {
		long amount = costClubCardDao.getAmount();
		List<AgentCostClubCard> cardList = costClubCardDao.findCostClubCard(page, size);
		ListPage listPage = new ListPage(cardList, page, size, (int) amount);
		return listPage;
	}

	public void addCostClubCard(AgentCostClubCard card) {
		costClubCardDao.addCostClubCard(card);
	}

	public boolean deleteCostClubCard(String[] cardIds) {
		return costClubCardDao.deleteCostClubCard(cardIds);
	}
}
