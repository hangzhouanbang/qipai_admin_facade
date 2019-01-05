package com.anbang.qipai.admin.msg.receiver.resultreceiver;

import com.anbang.qipai.admin.msg.channel.sink.resultsink.DianpaoMajiangGameSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;
import java.util.Map;

@EnableBinding(DianpaoMajiangGameSink.class)
public class DianpaoMajiangGameMsgReceiver {
	@Autowired
	private GameService gameService;

	private Gson gson = new Gson();

	@StreamListener(DianpaoMajiangGameSink.DIANPAOMAJIANGGAME)
	public void receive(CommonMO mo) {
		String msg = mo.getMsg();
		if ("playerQuit".equals(msg)) {// 有人退出游戏
//			Map data = (Map) mo.getData();
//			String gameId = (String) data.get("gameId");
//			String playerId = (String) data.get("playerId");
//			GameRoom room = gameService.findRoomByGameAndServerGameGameId(Game.dianpaoMajiang, gameId);
//			List<PlayersRecord> playersRecord = room.getPlayersRecord();
//			if (room.isVip() && !memberService.findMember(playerId).isVip()) {
//				for (int i = 0; i < playersRecord.size(); i++) {
//					if (playersRecord.get(i).getPlayerId().equals(playerId)) {
//						// 退出玩家花费的玉石
//						int amount = playersRecord.get(i).getPayGold();
//						qipaiMembersRomoteService.gold_givegoldtomember(playerId, amount, "back gold to leave game");
//						// 删除玩家记录
//						playersRecord.remove(i);
//					}
//				}
//				gameService.saveGameRoom(room);
//			}
//			gameService.dianpaoMajiangPlayerQuitQame(gameId, playerId);
		}
		if ("ju finished".equals(msg)) {// 一局游戏结束
//			Map data = (Map) mo.getData();
//			String gameId = (String) data.get("gameId");
//			GameRoom gameRoom = gameService.findRoomByGameAndServerGameGameId(Game.dianpaoMajiang, gameId);
//			gameRoomCmdService.removeRoom(gameRoom.getNo());
//
//			List<PlayersRecord> playersRecord = gameRoom.getPlayersRecord();
//			// 一盘没有打完，返回玉石
//			for (int i = 0; i < playersRecord.size(); i++) {
//				if (gameRoom.getCurrentPanNum() == 0 && gameRoom.isVip() && !playersRecord.get(i).isVip()) {
//					int amount = playersRecord.get(i).getPayGold();
//					qipaiMembersRomoteService.gold_givegoldtomember(playersRecord.get(i).getPlayerId(), amount,
//							"back gold to leave game");
//				}
//				gameService.saveGameRoom(gameRoom);
//			}
//			gameService.gameRoomFinished(Game.dianpaoMajiang, gameId);

		}
		if ("pan finished".equals(msg)) {// 一盘游戏结束
//			Map data = (Map) mo.getData();
//			String gameId = (String) data.get("gameId");
//			int no = (int) data.get("no");
//			List playerIds = (List) data.get("playerIds");
//			gameService.panFinished(Game.dianpaoMajiang, gameId, no, playerIds);
		}
	}
}
