package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.GameServerSink;
import com.anbang.qipai.admin.plan.domain.games.GameServer;
import com.anbang.qipai.admin.plan.service.GameService;

import net.sf.json.JSONObject;

@EnableBinding(GameServerSink.class)
public class GameServerMsgReceiver {

	@Autowired
	private GameService gameService;

	@StreamListener(GameServerSink.GAMESERVER)
	public void gameServer(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		String msg = json.getString("msg");
		if (msg.equals("online")) {
			JSONObject obj = (JSONObject) json.get("data");
			GameServer gameServer = (GameServer) JSONObject.toBean(obj, GameServer.class);
			gameService.onlineGameServer(gameServer);
		} else if (msg.equals("offline")) {
			String gameServerId = json.getString("data");
			gameService.offlineGameServer(gameServerId);
		}
	}

}
