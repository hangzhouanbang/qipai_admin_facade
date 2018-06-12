package com.anbang.qipai.admin.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.GameServerDao;
import com.anbang.qipai.admin.plan.domain.games.Game;
import com.anbang.qipai.admin.plan.domain.games.GameServer;

@Component
public class GameService {

	@Autowired
	private GameServerDao gameServerDao;

	public void onlineGameServer(GameServer gameServer) {
		gameServerDao.save(gameServer);
	}

	public void offlineGameServer(String gameServerId) {
		gameServerDao.remove(gameServerId);
	}

	public List<GameServer> findAllServersForGame(Game game) {
		return gameServerDao.findAllByGame(game);
	}

}
