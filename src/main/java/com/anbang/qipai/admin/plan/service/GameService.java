package com.anbang.qipai.admin.plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.GameServerDao;
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

}
