package com.anbang.qipai.admin.plan.dao;

import com.anbang.qipai.admin.plan.domain.games.GameServer;

public interface GameServerDao {

	void save(GameServer gameServer);

	void remove(String id);

}
