package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GameServerSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.domain.games.GameServer;
import com.anbang.qipai.admin.plan.service.GameService;
import com.google.gson.Gson;

@EnableBinding(GameServerSink.class)
public class GameServerMsgReceiver {

	@Autowired
	private GameService gameService;

	private Gson gson = new Gson();

	@StreamListener(GameServerSink.GAMESERVER)
	public void gameServer(CommonMO mo) {
		if ("online".equals(mo.getMsg())) {
			String json = gson.toJson(mo.getData());
			GameServer gameServer = gson.fromJson(json, GameServer.class);
			gameService.onlineGameServer(gameServer);
		}
		if ("offline".equals(mo.getMsg())) {
			String json = gson.toJson(mo.getData());
			String gameServerId = gson.fromJson(json, String.class);
			gameService.offlineGameServer(gameServerId);
		}
	}

}
