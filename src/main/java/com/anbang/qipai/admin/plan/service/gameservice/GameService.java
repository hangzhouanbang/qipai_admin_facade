package com.anbang.qipai.admin.plan.service.gameservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
import com.anbang.qipai.admin.plan.dao.gamedao.GameLawDao;
import com.anbang.qipai.admin.plan.dao.gamedao.GameServerDao;
import com.anbang.qipai.admin.plan.dao.gamedao.LawsMutexGroupDao;
import com.highto.framework.web.page.ListPage;

@Component
public class GameService {

	@Autowired
	private GameLawDao gameLawDao;

	@Autowired
	private LawsMutexGroupDao lawsMutexGroupDao;

	@Autowired
	private GameServerDao gameServerDao;

	public void onlineGameServer(GameServer gameServer) {
		gameServerDao.save(gameServer);
	}

	public void offlineGameServer(String[] ids) {
		gameServerDao.remove(ids);
	}

	public List<GameServer> findAllServersForGame(Game game) {
		return gameServerDao.findAllByGame(game);
	}

	public void createGameLaw(GameLaw law) {
		gameLawDao.save(law);
	}

	public void removeGameLaw(String lawId) {
		gameLawDao.remove(lawId);
	}

	public ListPage findGameLawByConditions(int page, int size, GameLaw law) {
		long amount = gameLawDao.getAmountByConditions(law);
		List<GameLaw> list = gameLawDao.findGameLawByConditions(page, size, law);
		ListPage listPage = new ListPage(list, page, size, (int) amount);
		return listPage;
	}

	public void addLawsMutexGroup(LawsMutexGroup lawsMutexGroup) {
		lawsMutexGroupDao.save(lawsMutexGroup);
	}

	public void removeLawsMutexGroup(String groupId) {
		lawsMutexGroupDao.remove(groupId);
	}

	public ListPage findLawsMutexGroupByConditions(int page, int size, LawsMutexGroup lawsMutexGroup) {
		long amount = lawsMutexGroupDao.getAmountByConditions(lawsMutexGroup);
		List<LawsMutexGroup> list = lawsMutexGroupDao.findLawsMutexGroupByConditions(page, size, lawsMutexGroup);
		ListPage listPage = new ListPage(list, page, size, (int) amount);
		return listPage;
	}
}
