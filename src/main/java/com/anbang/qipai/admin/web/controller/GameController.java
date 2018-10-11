package com.anbang.qipai.admin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.plan.bean.games.Game;
import com.anbang.qipai.admin.plan.bean.games.GameLaw;
import com.anbang.qipai.admin.plan.bean.games.GameServer;
import com.anbang.qipai.admin.plan.bean.games.LawsMutexGroup;
import com.anbang.qipai.admin.plan.service.gameservice.GameService;
import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private QipaiGameRemoteService qipaiGameRomoteService;

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/servers_for_game", method = RequestMethod.POST)
	public CommonVO serversforgame(Game game) {
		CommonVO vo = new CommonVO();
		List<GameServer> serversForGame = gameService.findAllServersForGame(game);
		Map data = new HashMap();
		data.put("servers", serversForGame);
		vo.setData(data);
		return vo;
	}

	@RequestMapping(value = "/game_server_online", method = RequestMethod.POST)
	public CommonVO gameserveronline(GameServer gameServer) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_gameServerOnline(gameServer);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	@RequestMapping(value = "/game_server_offline", method = RequestMethod.POST)
	public CommonVO gameserveroffline(@RequestParam(value = "gameServerId") String[] gameServerIds) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_gameServerOffline(gameServerIds);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	/**
	 * 停止指定id的服务器
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public CommonVO stopGameServer(@RequestParam("ids[]") List<String> ids) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(true);
		try {
			this.gameService.stopGameServersAndSendMsg(ids);
		} catch (Exception e) {
			LOGGER.warn("stop game server -> {} failed ", ids);
			vo.setSuccess(false);
		}
		return vo;
	}

	/**
	 * 运行指定id的服务器
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/recover", method = RequestMethod.POST)
	public CommonVO recoverGameServer(@RequestParam("ids[]") List<String> ids) {
		CommonVO vo = new CommonVO();
		vo.setSuccess(true);
		try {
			this.gameService.recoverGameServersAndSendMsg(ids);
		} catch (Exception e) {
			LOGGER.warn("recover game server -> {} failed ", ids);
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "/query_gamelaw", method = RequestMethod.POST)
	public CommonVO queryGameLaw(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, GameLaw law) {
		CommonVO vo = new CommonVO();
		ListPage listPage = gameService.findGameLawByConditions(page, size, law);
		vo.setMsg("gamelaw list");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/add_law", method = RequestMethod.POST)
	public CommonVO addlaw(GameLaw law) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_addLaw(law);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	@RequestMapping(value = "/update_law", method = RequestMethod.POST)
	public CommonVO updatelaw(GameLaw law) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_updateLaw(law);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	@RequestMapping(value = "/remove_law", method = RequestMethod.POST)
	public CommonVO removelaw(String lawId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_removelaw(lawId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	@RequestMapping(value = "/query_lawsmutexgroup", method = RequestMethod.POST)
	public CommonVO queryLawsMutexGroup(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, LawsMutexGroup lawsMutexGroup) {
		CommonVO vo = new CommonVO();
		ListPage listPage = gameService.findLawsMutexGroupByConditions(page, size, lawsMutexGroup);
		vo.setMsg("lawsMutexGroup list");
		vo.setData(listPage);
		return vo;
	}

	@RequestMapping(value = "/add_mutexgroup", method = RequestMethod.POST)
	public CommonVO addmutexgroup(LawsMutexGroup lawsMutexGroup) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_addmutexgroup(lawsMutexGroup);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

	@RequestMapping(value = "/remove_mutexgroup", method = RequestMethod.POST)
	public CommonVO removemutexgroup(String groupId) {
		CommonVO vo = new CommonVO();
		CommonRemoteVO rvo = qipaiGameRomoteService.game_removemutexgroup(groupId);
		vo.setSuccess(rvo.isSuccess());
		return vo;
	}

}
