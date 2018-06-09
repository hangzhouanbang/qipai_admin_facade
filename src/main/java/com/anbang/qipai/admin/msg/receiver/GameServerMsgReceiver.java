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
		JSONObject obj = (JSONObject) json.get("data");
		if (msg.equals("online")) {
			GameServer gameServer = (GameServer) JSONObject.toBean(obj, GameServer.class);
			gameService.onlineGameServer(gameServer);
		} else if (msg.equals("offline")) {
			String gameServerId = obj.toString();
			gameService.offlineGameServer(gameServerId);
		}
	}

}
