package com.anbang.qipai.admin.msg.receiver.resultreceiver;

import com.anbang.qipai.admin.msg.channel.sink.resultsink.RuianMajiangResultSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.msjobj.MajiangHistoricalJuResultMO;
import com.anbang.qipai.admin.msg.msjobj.ResultEnum;
import com.anbang.qipai.admin.plan.bean.games.GameRoom;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameHistoricalJuResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameHistoricalPanResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GameJuPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.GamePanPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.majiang.RuianMajiangJuPlayerResult;
import com.anbang.qipai.admin.plan.bean.historicalresult.majiang.RuianMajiangPanPlayerResult;
import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.service.gameservice.GameHistoricalJuResultService;
import com.anbang.qipai.admin.plan.service.gameservice.GameHistoricalPanResultService;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.anbang.qipai.admin.plan.service.reportservice.GameResultMsgService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnableBinding(RuianMajiangResultSink.class)
public class RuianMajiangResultMsgReceiver {

    @Autowired
    private GameResultMsgService gameResultMsgService;

    @Autowired
    private GameHistoricalJuResultService majiangHistoricalResultService;

    @Autowired
    private GameHistoricalPanResultService majiangHistoricalPanResultService;

    @Autowired
    private GameService gameService;

    private Gson gson = new Gson();

	@StreamListener(RuianMajiangResultSink.RUIANMAJIANGRESULT)
	public void recordMajiangHistoricalResult(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        //解析接受的消息
        MajiangHistoricalJuResultMO result=gson.fromJson(json,MajiangHistoricalJuResultMO.class);
        if (ResultEnum.RUIAN_JU_RESULT.getMessage().equals(msg)) {
            gameResultMsgService.recordGameResult(result);
        }

        //游戏局盘记录存储
        Map map = gson.fromJson(json, Map.class);
        if ("ruianmajiang ju result".equals(msg)) {
            String gameId = (String) map.get("gameId");
            GameHistoricalJuResult majiangHistoricalResult = new GameHistoricalJuResult();
            majiangHistoricalResult.setGameId(gameId);
            GameRoom room = gameService.findRoomByGameAndServerGameGameId(Game.ruianMajiang, gameId);
            majiangHistoricalResult.setRoomNo(room.getNo());
            majiangHistoricalResult.setGame(Game.ruianMajiang);
            majiangHistoricalResult.setDayingjiaId((String) map.get("dayingjiaId"));
            majiangHistoricalResult.setDatuhaoId((String) map.get("datuhaoId"));

            List<GameJuPlayerResult> juPlayerResultList = new ArrayList<>();
            ((List) map.get("playerResultList")).forEach(
                    (juPlayerResult) -> juPlayerResultList.add(new RuianMajiangJuPlayerResult((Map) juPlayerResult)));
            majiangHistoricalResult.setPlayerResultList(juPlayerResultList);

            majiangHistoricalResult.setPanshu(((Double) map.get("panshu")).intValue());
            majiangHistoricalResult.setLastPanNo(((Double) map.get("lastPanNo")).intValue());
            majiangHistoricalResult.setFinishTime(((Double) map.get("finishTime")).longValue());

            majiangHistoricalResultService.addGameHistoricalResult(majiangHistoricalResult);
        }
        if ("ruianmajiang pan result".equals(msg)) {
            String gameId = (String) map.get("gameId");
            GameHistoricalPanResult majiangHistoricalResult = new GameHistoricalPanResult();
            majiangHistoricalResult.setGameId(gameId);
            majiangHistoricalResult.setGame(Game.ruianMajiang);

            List<GamePanPlayerResult> panPlayerResultList = new ArrayList<>();
            ((List) map.get("playerResultList")).forEach((panPlayerResult) -> panPlayerResultList
                    .add(new RuianMajiangPanPlayerResult((Map) panPlayerResult)));
            majiangHistoricalResult.setPlayerResultList(panPlayerResultList);

            majiangHistoricalResult.setNo(((Double) map.get("no")).intValue());
            majiangHistoricalResult.setFinishTime(((Double) map.get("finishTime")).longValue());

            majiangHistoricalPanResultService.addGameHistoricalResult(majiangHistoricalResult);
        }
	}
}
