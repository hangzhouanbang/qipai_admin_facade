package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.GameServerDao;
import com.anbang.qipai.admin.plan.dao.mongodb.repository.GameServerRepository;
import com.anbang.qipai.admin.plan.domain.games.Game;
import com.anbang.qipai.admin.plan.domain.games.GameServer;

@Component
public class MongodbGameServerDao implements GameServerDao {

	@Autowired
	private GameServerRepository repository;

	@Override
	public void save(GameServer gameServer) {
		repository.save(gameServer);
	}

	@Override
	public void remove(String id) {
		repository.delete(id);
	}

	@Override
	public List<GameServer> findAllByGame(Game game) {
		return repository.findByGame(game);
	}

}
