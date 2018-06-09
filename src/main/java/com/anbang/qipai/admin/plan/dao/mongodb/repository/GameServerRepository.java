package com.anbang.qipai.admin.plan.dao.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.games.GameServer;

public interface GameServerRepository extends MongoRepository<GameServer, String> {

}
