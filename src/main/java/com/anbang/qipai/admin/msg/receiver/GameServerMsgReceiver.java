package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GameServerSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.google.gson.Gson;

@EnableBinding(GameServerSink.class)
public class GameServerMsgReceiver {

	@Autowired
	private GameService gameService;

	private Gson gson = new Gson();

	@StreamListener(GameServerSink.GAMESERVER)
	public void gameServer(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("online".equals(msg)) {
			GameServer gameServer = gson.fromJson(json, GameServer.class);
			gameService.onlineGameServer(gameServer);
		}
		if ("offline".equals(msg)) {
			String gameServerId = gson.fromJson(json, String.class);
			gameService.offlineGameServer(gameServerId);
		}
		if ("create gamelaw".equals(msg)) {
			GameLaw law = gson.fromJson(json, GameLaw.class);
			gameService.createGameLaw(law);
		}
		if ("remove gamelaw".equals(msg)) {
			String lawId = gson.fromJson(json, String.class);
			gameService.removeGameLaw(lawId);
		}
		if ("create lawsmutexgroup".equals(msg)) {
			LawsMutexGroup lawsMutexGroup = gson.fromJson(json, LawsMutexGroup.class);
			gameService.addLawsMutexGroup(lawsMutexGroup);
		}
		if ("remove lawsmutexgroup".equals(msg)) {
			String groupId = gson.fromJson(json, String.class);
			gameService.removeLawsMutexGroup(groupId);
		}
	}

}
