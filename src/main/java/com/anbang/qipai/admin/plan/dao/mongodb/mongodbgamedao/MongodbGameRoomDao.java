package com.anbang.qipai.admin.plan.dao.mongodb.mongodbgamedao;

import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameRoom;
import com.anbang.qipai.admin.plan.dao.gamedao.GameRoomDao;
import com.anbang.qipai.admin.plan.dao.mongodb.repository.GameRoomRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongodbGameRoomDao implements GameRoomDao {

	@Autowired
	private GameRoomRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(GameRoom gameRoom) {
		repository.save(gameRoom);
	}

	@Override
	public int count(long startTimeForCreate, long endTimeForCreate, String createMemberId, boolean vip) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		//gt:大于 lt:小于
		criteria.andOperator(Criteria.where("createTime").gt(startTimeForCreate),
				Criteria.where("createTime").lt(endTimeForCreate), Criteria.where("createMemberId").is(createMemberId),
				Criteria.where("vip").is(vip));
		query.addCriteria(criteria);
		return (int) mongoTemplate.count(query, GameRoom.class);
	}

	@Override
	public GameRoom findRoomOpen(String roomNo) {
		return repository.findByNoAndFinished(roomNo, false);
	}

	@Override
	public List<GameRoom> findExpireGameRoom(long deadlineTime, boolean finished) {
		Query query = new Query();
		query.addCriteria(Criteria.where("deadlineTime").lte(deadlineTime));
		query.addCriteria(Criteria.where("finished").is(finished));
		return mongoTemplate.find(query, GameRoom.class);
	}

	@Override
	public void updateGameRoomFinished(List<String> ids, boolean finished) {
		Query query = new Query(Criteria.where("id").in(ids));
		Update update = new Update();
		update.set("finished", finished);
		mongoTemplate.updateMulti(query, update, GameRoom.class);
	}

	@Override
	public void updateFinishGameRoom(Game game, String serverGameId, boolean finished) {
		Query query = new Query();
		query.addCriteria(Criteria.where("game").is(game));
		query.addCriteria(Criteria.where("serverGame.gameId").is(serverGameId));
		Update update = new Update();
		update.set("finished", finished);
		mongoTemplate.updateFirst(query, update, GameRoom.class);
	}

	@Override
	public void updateGameRoomCurrentPanNum(Game game, String serverGameId, int no) {
		Query query = new Query();
		query.addCriteria(
				Criteria.where("game").is(game).andOperator(Criteria.where("serverGame.gameId").is(serverGameId)));
		Update update = new Update();
		update.set("currentPanNum", no);
		mongoTemplate.updateFirst(query, update, GameRoom.class);
	}

	@Override
	public GameRoom findRoomByGameAndServerGameGameId(Game game, String serverGameId) {
		Query query = new Query();
		query.addCriteria(
				Criteria.where("game").is(game).andOperator(Criteria.where("serverGame.gameId").is(serverGameId)));
		return mongoTemplate.findOne(query, GameRoom.class);
	}

	@Override
	public int countRoomByPlayerIdAndRoomNo(String playerId, String roomNo) {
		Query query = new Query();
		if (StringUtils.isNotBlank(playerId)) {
			query.addCriteria(Criteria.where("playersRecord.playerId").is(playerId));
		}
		if (StringUtils.isNotBlank(roomNo)) {
			query.addCriteria(Criteria.where("no").is(roomNo));
		}
		return (int)mongoTemplate.count(query, GameRoom.class);
	}

	@Override
	public List<GameRoom> findRoomByPlayerIdAndRoomNo(int page, int size, String playerId, String roomNo) {
		Query query = new Query();
		if (StringUtils.isNotBlank(playerId)) {
			query.addCriteria(Criteria.where("playersRecord.playerId").is(playerId));
		}
		if (StringUtils.isNotBlank(roomNo)) {
			query.addCriteria(Criteria.where("no").is(roomNo));
		}
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
		query.skip((page - 1) * size);
		query.limit(size);
		return mongoTemplate.find(query, GameRoom.class);
	}

}
