package com.anbang.qipai.admin.msg.receiver.resultreceiver;

import com.anbang.qipai.admin.msg.channel.sink.resultsink.PaodekuaiResultSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameRoom;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameHistoricalJuResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameHistoricalPanResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameJuPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GamePanPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.puke.PaodekuaiJuPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.puke.PaodekuaiPanPlayerResult;
import com.anbang.qipai.admin.plan.service.gameservice.GameHistoricalJuResultService;
import com.anbang.qipai.admin.plan.service.gameservice.GameHistoricalPanResultService;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@EnableBinding(PaodekuaiResultSink.class)
public class PaodekuaiResultMsgReceiver {

	@Autowired
	private GameHistoricalJuResultService gameHistoricalResultService;

	@Autowired
	private GameHistoricalPanResultService gameHistoricalPanResultService;

	@Autowired
	private GameService gameService;

	private Gson gson = new Gson();

	@StreamListener(PaodekuaiResultSink.PAODEKUAIRESULT)
	public void recordMajiangHistoricalResult(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());

		//游戏局盘记录存储
		Map map = gson.fromJson(json, Map.class);
		if ("paodekuai ju result".equals(msg)) {
			String gameId = (String) map.get("gameId");
			GameHistoricalJuResult pukeHistoricalResult = new GameHistoricalJuResult();
			pukeHistoricalResult.setGameId(gameId);
			GameRoom room = gameService.findRoomByGameAndServerGameGameId(Game.paodekuai, gameId);
			pukeHistoricalResult.setRoomNo(room.getNo());
			pukeHistoricalResult.setGame(Game.paodekuai);
			pukeHistoricalResult.setDayingjiaId((String) map.get("dayingjiaId"));
			pukeHistoricalResult.setDatuhaoId((String) map.get("datuhaoId"));

			List<GameJuPlayerResult> juPlayerResultList = new ArrayList<>();
			((List) map.get("playerResultList")).forEach((juPlayerResult) -> juPlayerResultList
					.add(new PaodekuaiJuPlayerResult((Map) juPlayerResult)));
			pukeHistoricalResult.setPlayerResultList(juPlayerResultList);

			pukeHistoricalResult.setPanshu(((Double) map.get("panshu")).intValue());
			pukeHistoricalResult.setLastPanNo(((Double) map.get("lastPanNo")).intValue());
			pukeHistoricalResult.setFinishTime(((Double) map.get("finishTime")).longValue());

			gameHistoricalResultService.addGameHistoricalResult(pukeHistoricalResult);
		}
		if ("paodekuai pan result".equals(msg)) {
			String gameId = (String) map.get("gameId");
			GameHistoricalPanResult pukeHistoricalResult = new GameHistoricalPanResult();
			pukeHistoricalResult.setGameId(gameId);
			pukeHistoricalResult.setGame(Game.paodekuai);

			List<GamePanPlayerResult> panPlayerResultList = new ArrayList<>();
			((List) map.get("playerResultList")).forEach((panPlayerResult) -> panPlayerResultList
					.add(new PaodekuaiPanPlayerResult((Map) panPlayerResult)));
			pukeHistoricalResult.setPlayerResultList(panPlayerResultList);

			pukeHistoricalResult.setNo(((Double) map.get("no")).intValue());
			pukeHistoricalResult.setFinishTime(((Double) map.get("finishTime")).longValue());

			gameHistoricalPanResultService.addGameHistoricalResult(pukeHistoricalResult);
		}
	}
}
