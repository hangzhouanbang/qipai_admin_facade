package com.anbang.qipai.admin.plan.dao;

import java.util.List;

import com.anbang.qipai.admin.plan.domain.games.Game;
import com.anbang.qipai.admin.plan.domain.games.GameServer;

public interface GameServerDao {

	void save(GameServer gameServer);

	void remove(String id);

	List<GameServer> findAllByGame(Game game);

}
