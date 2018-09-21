package com.anbang.qipai.admin.plan.service.gameservice;

import com.anbang.qipai.admin.msg.channel.source.GameServerManagerSource;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.receiver.GameServerMsgConstant;
import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
import com.anbang.qipai.admin.plan.dao.gamedao.GameLawDao;
import com.anbang.qipai.admin.plan.dao.gamedao.GameServerDao;
import com.anbang.qipai.admin.plan.dao.gamedao.LawsMutexGroupDao;
import com.highto.framework.web.page.ListPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableBinding(GameServerManagerSource.class)
public class GameService {

    public static final int GAME_SERVER_STATE_RUNNING=0;
    public static final int GAME_SERVER_STATE_STOP=1;

	@Autowired
	private GameLawDao gameLawDao;

	@Autowired
	private LawsMutexGroupDao lawsMutexGroupDao;

	@Autowired
	private GameServerDao gameServerDao;

	@Autowired
	private GameServerManagerSource gameServerManagerSource;

	public void onlineGameServer(GameServer gameServer) {
		gameServerDao.save(gameServer);
	}

	public void offlineGameServer(String[] ids) {
		gameServerDao.remove(ids);
	}

	public List<GameServer> findAllServersForGame(Game game) {
		return gameServerDao.findAllByGame(game);
	}

	public void createGameLaw(GameLaw law) {
		gameLawDao.save(law);
	}

	public void removeGameLaw(String lawId) {
		gameLawDao.remove(lawId);
	}

	public ListPage findGameLawByConditions(int page, int size, GameLaw law) {
		long amount = gameLawDao.getAmountByConditions(law);
		List<GameLaw> list = gameLawDao.findGameLawByConditions(page, size, law);
		ListPage listPage = new ListPage(list, page, size, (int) amount);
		return listPage;
	}

	public void addLawsMutexGroup(LawsMutexGroup lawsMutexGroup) {
		lawsMutexGroupDao.save(lawsMutexGroup);
	}

	public void removeLawsMutexGroup(String groupId) {
		lawsMutexGroupDao.remove(groupId);
	}

	public ListPage findLawsMutexGroupByConditions(int page, int size, LawsMutexGroup lawsMutexGroup) {
		long amount = lawsMutexGroupDao.getAmountByConditions(lawsMutexGroup);
		List<LawsMutexGroup> list = lawsMutexGroupDao.findLawsMutexGroupByConditions(page, size, lawsMutexGroup);
		ListPage listPage = new ListPage(list, page, size, (int) amount);
		return listPage;
	}

	public void stopGameServersAndSendMsg(List<String> ids) {
	    if (ids!=null && ids.size()>0){
            CommonMO commonMO = new CommonMO();
            commonMO.setMsg(GameServerMsgConstant.STOP_GAME_SERVERS);
            commonMO.setData(ids);
            //TODO Stream 异步发送
            this.gameServerManagerSource.gameServerManager().send(MessageBuilder.withPayload(commonMO).build());
            this.stopGameServers(ids);
        }
	}

	public void recoverGameServersAndSendMsg(List<String> ids){
	    if (ids != null && ids.size() > 0){
	        CommonMO commonMO = new CommonMO();
	        commonMO.setMsg(GameServerMsgConstant.RECOVER_GAME_SERVERS);
	        commonMO.setData(ids);
            this.gameServerManagerSource.gameServerManager().send(MessageBuilder.withPayload(commonMO).build());
            this.startGameServers(ids);
        }
    }


    public void startGameServers(List<String> ids) {
        if (ids!=null && ids.size() > 0){
            this.gameServerDao.updateGameServerState(ids,GameService.GAME_SERVER_STATE_RUNNING);
        }
    }

    public void stopGameServers(List<String> ids){
        if (ids!=null && ids.size() > 0){
            this.gameServerDao.updateGameServerState(ids,GameService.GAME_SERVER_STATE_STOP);
        }
    }


}
